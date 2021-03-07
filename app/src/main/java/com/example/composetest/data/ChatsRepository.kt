package com.example.composetest.data

interface ChatsRepository {
    fun getAllChats(): List<Chat>
}