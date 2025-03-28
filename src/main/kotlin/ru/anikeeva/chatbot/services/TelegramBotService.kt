package ru.anikeeva.chatbot.services

import org.springframework.ai.chat.messages.UserMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Update
import ru.anikeeva.chatbot.cache.MessageCache
import ru.anikeeva.chatbot.utils.createMessage

@Service
class TelegramBotService (
    private val openAiService: OpenAiService,
    private val messageCache: MessageCache,
    commands: Set<BotCommand>,
    @Value("\${telegram.token}")
    token: String
) : TelegramLongPollingCommandBot(token) {
    @Value("\${telegram.botName}")
    private val botName: String = ""

    init {
        registerAll(*commands.toTypedArray())
    }

    override fun getBotUsername(): String = botName

    override fun processNonCommandUpdate(update: Update) {
        if (update.hasMessage()) {
            val chatId = update.message.chatId
            if (update.message.hasText()) {
                val messageText = update.message.text
                val messages = messageCache.getOrInitMessages(chatId).toMutableList()
                messages += UserMessage(messageText)
                val assistantMessages = openAiService.sendMessage(messages)
            }
            else {
                execute(createMessage(chatId.toString(), "Я понимаю только текст :("))
            }
        }
    }
}