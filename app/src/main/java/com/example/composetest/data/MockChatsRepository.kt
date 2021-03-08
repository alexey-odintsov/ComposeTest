package com.example.composetest.data

import com.example.composetest.data.source.RemoteDataSource
import com.example.composetest.model.Chat

class MockChatsRepository(
    private val remoteDataSource: RemoteDataSource
) : ChatsRepository {
    override suspend fun fetchChatInfo(chatId: Long): Chat? {
        return remoteDataSource.fetchChatInfo(chatId)
    }

    override suspend fun sendMessage(userId: Int, chatId: Long, text: String) {
        remoteDataSource.sendMessage(userId, chatId, text)
    }

    override suspend fun getChats(page: Int): List<Chat> {
        return remoteDataSource.getChats(page)
    }
}