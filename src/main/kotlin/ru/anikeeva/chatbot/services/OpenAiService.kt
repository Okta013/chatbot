package ru.anikeeva.chatbot.services

import org.springframework.ai.chat.messages.Message
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.ai.openai.api.OpenAiApi
import org.springframework.ai.openai.api.ResponseFormat
import org.springframework.stereotype.Service

@Service
class OpenAiService(
    private val chatModel: OpenAiChatModel
) {
    fun sendMessage(context: List<Message>): List<String> {
        val response = chatModel.call(
            Prompt(
                context,
                OpenAiChatOptions.builder()
                    .model(OpenAiApi.ChatModel.GPT_4_O)
                    .temperature(1.0)
                    .responseFormat(ResponseFormat.builder().type(ResponseFormat.Type.TEXT).build())
                    .build()
            )
        )
        return response.results
            ?.map {
                it.output.text
            }
            ?: emptyList()
    }
}