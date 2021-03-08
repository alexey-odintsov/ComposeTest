package com.example.composetest.data

import com.example.composetest.model.Chat

interface ChatsRepository {
    suspend fun getAllChats(): List<Chat>
    suspend fun fetchChatInfo(chatId: Long): Chat?
}