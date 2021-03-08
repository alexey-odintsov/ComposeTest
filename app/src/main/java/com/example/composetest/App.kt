package com.example.composetest

import android.app.Application
import com.example.composetest.data.ChatsRepository
import com.example.composetest.data.MockChatsRepository
import com.example.composetest.data.source.MockRemoteDataSource

class App : Application(), RepositoryProvider {

    private val repository = MockChatsRepository(MockRemoteDataSource(1500))

    override fun getRepository(): ChatsRepository {
        return repository
    }
}

interface RepositoryProvider {
    fun getRepository(): ChatsRepository
}