package ru.anikeeva.chatbot.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.anikeeva.chatbot.cache.MessageCache
import ru.anikeeva.chatbot.utils.createMessage

@Component
class StartCommand (
    private val messageCache: MessageCache
): BotCommand("/start", "") {
    override fun execute(absSender: AbsSender, user: User, chat: Chat, args: Array<out String>) {
        messageCache.clearMessages(chat.id)
        absSender.execute(createMessage(chat.id.toString(), "Привет! Начинаем диалог, задай свой вопрос"))
    }
}