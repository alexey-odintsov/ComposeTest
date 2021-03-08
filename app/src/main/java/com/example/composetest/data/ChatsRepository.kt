package com.example.composetest.data

import com.example.composetest.model.Chat
import com.example.composetest.model.Message

interface ChatsRepository {
    suspend fun getAllChats(): List<Chat>
    suspend fun getChatMessages(chatId: Long): List<Message>
    suspend fun fetchChatInfo(chatId: Long): Chat?
}