package com.example.composetest.data

import com.example.composetest.model.Chat
import com.example.composetest.model.Message

interface ChatsRepository {
    fun getAllChats(): List<Chat>
    fun getChatMessages(chatId: Long): List<Message>
    fun fetchChatInfo(chatId: Long): Chat?
}