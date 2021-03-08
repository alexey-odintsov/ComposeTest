package com.example.composetest.data

import com.example.composetest.data.source.RemoteDataSource
import com.example.composetest.model.Chat
import com.example.composetest.model.Message

class MockChatsRepository(
    private val remoteDataSource: RemoteDataSource
) : ChatsRepository {
    override suspend fun getAllChats(): List<Chat> {
        return remoteDataSource.getAllChats()
    }

    override suspend fun getChatMessages(chatId: Long): List<Message> {
        return remoteDataSource.getChatMessage(chatId)
    }

    override suspend fun fetchChatInfo(chatId: Long): Chat? {
        return remoteDataSource.fetchChatInfo(chatId)
    }
}