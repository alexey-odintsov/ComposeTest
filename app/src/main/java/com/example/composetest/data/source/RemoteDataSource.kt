package com.example.composetest.data.source

import com.example.composetest.model.Chat
import com.example.composetest.model.Message

interface RemoteDataSource {
    suspend fun getAllChats(): List<Chat>
    suspend fun getChatMessage(chatId: Long): List<Message>
    suspend fun fetchChatInfo(chatId: Long): Chat?
}