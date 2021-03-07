package com.example.composetest.data

import com.example.composetest.data.source.RemoteDataSource

class MockChatsRepository(
    private val remoteDataSource: RemoteDataSource
) : ChatsRepository {
    override fun getAllChats(): List<Chat> {
        return remoteDataSource.getAllChats()
    }
}