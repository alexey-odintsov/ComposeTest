package com.example.composetest.data.source

import com.example.composetest.model.Chat
import com.example.composetest.model.Message
import com.example.composetest.model.TextContent
import com.example.composetest.model.User
import kotlinx.coroutines.delay

class MockRemoteDataSource(private val delayMs: Long = 0L) : RemoteDataSource {
    private var messageIdx = 0L
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
        Chat(
            1, "Best friends' chat", 1614965428L,
            mutableListOf(
                Message(++messageIdx, 1, users[0], 1614965428L, TextContent("Hi Jimmy, howdy?")),
                Message(++messageIdx, 1, users[0], 1612632634L, TextContent("Wanna go coffee?")),
                Message(++messageIdx, 1, users[1], 1612632754L, TextContent("Hey dude, sure!")),
            )
        ),
        Chat(
            2, "New movie", 1612632628L, mutableListOf(
                Message(
                    ++messageIdx,
                    2,
                    users[2],
                    1612697554L,
                    TextContent("Keanu, have you read the script?")
                ),
                Message(++messageIdx, 2, users[2], 1612698754L, TextContent("Hey, answer me")),
                Message(++messageIdx, 2, users[2], 1612699174L, TextContent("I'm waiting...")),
                Message(
                    ++messageIdx,
                    2,
                    users[0],
                    1612708534L,
                    TextContent("Nope yet, I will, I promise")
                ),
            )
        ),
        Chat(
            3, "Oscar winner", 1615965428L, mutableListOf(
                Message(++messageIdx, 3, users[0], 1612935628L, TextContent("Who will win?")),
                Message(++messageIdx, 3, users[1], 1602935728L, TextContent("Not you! )")),
                Message(++messageIdx, 3, users[1], 1602945728L, TextContent("Me? )")),
                Message(++messageIdx, 3, users[0], 1602945728L, TextContent("Me? )")),
                Message(++messageIdx, 3, users[2], 1602945728L, TextContent("Me? )")),
                Message(++messageIdx, 3, users[1], 1602945728L, TextContent("No")),
                Message(++messageIdx, 3, users[1], 1602945728L, TextContent("Ha-Ha")),
                Message(++messageIdx, 3, users[1], 1602945728L, TextContent("Choke")),
                Message(++messageIdx, 3, users[1], 1602945728L, TextContent("Catch me")),
                Message(++messageIdx, 3, users[1], 1602945728L, TextContent("Lol hiwnd  jjd rldk gdsrk jhdkj hdkjr dkjh gldkrjh kdrjsh lksjdhglkdrsjdlkjdlrjgdlrsjgdlrsgjhdlrskjgdsgjhrlkjdrj gdsr gd  sdkjrhg kdrsh gkjdrskdsrkhgdsk rkgh  rd")),
            )
        ),
        Chat(4, "Need work", 1592932628L, mutableListOf()),
        Chat(5, "Let's poker today", 1522932628L, mutableListOf()),
        Chat(6, "Billy's birthday", 1610030134L, mutableListOf()),
        Chat(7, "Holidays", 1610030134L, mutableListOf()),
        Chat(8, "Macy's", 1610030134L, mutableListOf()),
        Chat(9, "Mac M1, will you buy it?", 1610030134L, mutableListOf()),
        Chat(10, "Tesla", 1610030134L, mutableListOf()),
        Chat(11, "Mom's club", 1610030134L, mutableListOf()),
        Chat(12, "Tom's birthday", 1614848400L, mutableListOf()),
        Chat(13, "Biden? really?", 1610030134L, mutableListOf()),
        Chat(14, "Car broken, find repair", 1610030134L, mutableListOf()),
        Chat(15, "Kids presents", 1610030134L, mutableListOf()),
        Chat(16, "Lockdown again (", 1610030134L, mutableListOf()),
        Chat(17, "Father's day", 1610030134L, mutableListOf()),
        Chat(18, "They know about it..", 1610030134L, mutableListOf()),
        Chat(19, "Jetpack AMA", 1598518800L, mutableListOf()),
        Chat(20, "iOS or Android?", 1610030134L, mutableListOf()),
        Chat(21, "Experiment ", 1610030134L, mutableListOf()),
        Chat(22, "NASA rover where to find it?", 1610030134L, mutableListOf()),
        Chat(23, "Wages for living..", 1610030134L, mutableListOf()),
        Chat(24, "Best mortgage", 1610030134L, mutableListOf()),
        Chat(25, "Nintendo switch pro? when? prices?", 1610030134L, mutableListOf()),
        Chat(26, "Condo subleasing", 1610030134L, mutableListOf()),
        Chat(27, "Garage sale, friday, 10 am", 1610030134L, mutableListOf()),
        Chat(28, "st. Louise health dept", 1610030134L, mutableListOf()),
    )


    override suspend fun getAllChats(): List<Chat> {
        delay(delayMs)
        return chats.sortedByDescending { it.lastUpdated }
    }

    override suspend fun fetchChatInfo(chatId: Long): Chat? {
        delay(delayMs)
        return chats.find { it.id == chatId }
    }

    override suspend fun sendMessage(userId: Int, chatId: Long, text: String) {
        val chat = chats.find { it.id == chatId }!!
        val user = users.find { it.id == userId }!!
        chat.messages.add(
            Message(
                ++messageIdx,
                chatId,
                user,
                System.currentTimeMillis(),
                TextContent(text)
            )
        )
        chat.lastUpdated = System.currentTimeMillis()
    }
}