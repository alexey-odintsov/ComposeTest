package com.example.composetest.data.source

import com.example.composetest.model.Chat
import com.example.composetest.model.Message

interface RemoteDataSource {
    fun getAllChats(): List<Chat>
    fun getChatMessage(chatId: Long): List<Message>
}