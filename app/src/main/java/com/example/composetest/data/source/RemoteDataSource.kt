package com.example.composetest.data.source

import com.example.composetest.data.Chat

interface RemoteDataSource {
    fun getAllChats(): List<Chat>
}