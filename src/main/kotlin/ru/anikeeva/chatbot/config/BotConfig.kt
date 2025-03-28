package ru.anikeeva.chatbot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.anikeeva.chatbot.services.TelegramBotService

@Configuration
class BotConfig {
    @Bean
    fun telegramBotsApi(bot: TelegramBotService): TelegramBotsApi =
        TelegramBotsApi(DefaultBotSession::class.java).apply {
            registerBot(bot)
    }
}