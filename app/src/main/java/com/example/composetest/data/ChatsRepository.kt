package com.example.composetest.data

import com.example.composetest.model.Chat

interface ChatsRepository {
    suspend fun fetchChatInfo(chatId: Long): Chat?
    suspend fun sendMessage(userId: Int, chatId: Long, text: String)
    suspend fun getChats(page: Int): List<Chat>
}