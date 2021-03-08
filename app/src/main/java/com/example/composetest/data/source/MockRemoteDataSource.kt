package com.example.composetest.data.source

import com.example.composetest.model.Chat
import com.example.composetest.model.Message
import com.example.composetest.model.TextContent
import com.example.composetest.model.User

class MockRemoteDataSource : RemoteDataSource {

    private val users = listOf(
        User(
            1,
            "Keanu Reeves",
            "https://im0-tub-ru.yandex.net/i?id=998c8bdd9e968d02727dca2225cd5760&ref=rim&n=33&w=449&h=300"
        ),
        User(
            2,
            "Jim Carrey",
            "https://im0-tub-ru.yandex.net/i?id=66efd1f29b475da564cc1a49809f6be8&ref=rim&n=33&w=412&h=300"
        ),
        User(
            3,
            "Quentin Tarantino",
            "https://im0-tub-ru.yandex.net/i?id=7ba8d5c1880ddf47e8abcd5d8b0d87ed&ref=rim&n=33&w=480&h=240"
        ),
    )

    private val chats = listOf(
        Chat(1, "Best friends' chat", 1614965428L),
        Chat(2, "New movie", 1612632628L),
        Chat(3, "Oscar winner", 1602932628L),
        Chat(4, "Need work", 1592932628L),
        Chat(5, "Let's poker today", 1522932628L),
        Chat(6, "Billy's birthday", 1610030134L),
        Chat(7, "Holidays", 1610030134L),
        Chat(8, "Macy's", 1610030134L),
        Chat(9, "Mac M1, will you buy it?", 1610030134L),
        Chat(10, "Tesla", 1610030134L),
        Chat(11, "Mom's club", 1610030134L),
        Chat(12, "Tom's birthday", 1614848400L),
        Chat(13, "Biden? really?", 1610030134L),
        Chat(14, "Car broken, find repair", 1610030134L),
        Chat(15, "Kids presents", 1610030134L),
        Chat(16, "Lockdown again (", 1610030134L),
        Chat(17, "Father's day", 1610030134L),
        Chat(18, "They know about it..", 1610030134L),
        Chat(19, "Jetpack AMA", 1598518800L),
        Chat(20, "iOS or Android?", 1610030134L),
    )

    private val messages = listOf(
        Message(12345, 1, users[0], 1614965428L, TextContent("Hi Jimmy, howdy?")),
        Message(12346, 1, users[0], 1612632634L, TextContent("Wanna go coffee?")),
        Message(12347, 1, users[1], 1612632754L, TextContent("Hey dude, sure!")),
        Message(12348, 2, users[2], 1612697554L, TextContent("Keanu, have you read the script?")),
        Message(12349, 2, users[2], 1612698754L, TextContent("Hey, answer me")),
        Message(12350, 2, users[2], 1612699174L, TextContent("I'm waiting...")),
        Message(12351, 2, users[0], 1612708534L, TextContent("Nope yet, I will, I promise")),
        Message(12352, 3, users[0], 1612935628L, TextContent("Who will win?")),
        Message(12353, 3, users[1], 1602935728L, TextContent("Not you! )")),
    )


    override fun getAllChats(): List<Chat> {
        return chats.sortedByDescending { it.lastUpdated }
    }

    override fun getChatMessage(chatId: Long): List<Message> {
        return messages.filter { it.chatId == chatId }
    }

    override fun fetchChatInfo(chatId: Long): Chat? {
        return chats.find { it.id == chatId }
    }
}