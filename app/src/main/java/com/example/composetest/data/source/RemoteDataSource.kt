package com.example.composetest.data.source

import com.example.composetest.model.Chat

interface RemoteDataSource {
    suspend fun getAllChats(): List<Chat>
    suspend fun fetchChatInfo(chatId: Long): Chat?
    suspend fun sendMessage(userId: Int, chatId: Long, text: String)
}