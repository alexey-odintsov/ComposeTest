package com.example.composetest.data

import com.example.composetest.data.source.RemoteDataSource
import com.example.composetest.model.Chat
import com.example.composetest.model.Message

class MockChatsRepository(
    private val remoteDataSource: RemoteDataSource
) : ChatsRepository {
    override fun getAllChats(): List<Chat> {
        return remoteDataSource.getAllChats()
    }

    override fun getChatMessages(chatId: Long): List<Message> {
        return remoteDataSource.getChatMessage(chatId)
    }

    override fun fetchChatInfo(chatId: Long): Chat? {
        return remoteDataSource.fetchChatInfo(chatId)
    }
}