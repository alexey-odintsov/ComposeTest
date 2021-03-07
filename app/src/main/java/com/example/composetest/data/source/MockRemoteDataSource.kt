package com.example.composetest.data.source

import com.example.composetest.data.Chat

class MockRemoteDataSource : RemoteDataSource {

    private val chats = listOf(
        Chat(1, "Chat 1", 1232312L),
        Chat(2, "Chat 2", 23423432L),
        Chat(3, "Chat 3", 234234223L),
        Chat(4, "Chat 4", 23423432L),
        Chat(5, "Chat 5", 23423423432L),
        Chat(5, "Chat 5", 23423423432L),
    )


    override fun getAllChats(): List<Chat> {
        return chats
    }
}