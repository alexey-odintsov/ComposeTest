package com.example.composetest.data

import com.example.composetest.data.source.RemoteDataSource
import com.example.composetest.model.Chat

class MockChatsRepository(
    private val remoteDataSource: RemoteDataSource
) : ChatsRepository {
    override suspend fun getAllChats(): List<Chat> {
        return remoteDataSource.getAllChats()
    }

    override suspend fun fetchChatInfo(chatId: Long): Chat? {
        return remoteDataSource.fetchChatInfo(chatId)
    }
}