package ru.anikeeva.chatbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class ChatbotApplication

fun main(args: Array<String>) {
	runApplication<ChatbotApplication>(*args)
}
