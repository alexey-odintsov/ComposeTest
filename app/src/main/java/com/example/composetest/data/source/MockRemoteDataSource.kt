package com.example.composetest.data.source

import android.util.Log
import com.example.composetest.DAY
import com.example.composetest.MINUTE
import com.example.composetest.HOUR
import com.example.composetest.YEAR
import com.example.composetest.model.Chat
import com.example.composetest.model.Message
import com.example.composetest.model.TextContent
import com.example.composetest.model.User
import kotlinx.coroutines.delay

class MockRemoteDataSource(private val delayMs: Long = 0L) : RemoteDataSource {
    private var messageIdx = 0L
    private val today: Long = System.currentTimeMillis()
    private val yesterday: Long = today - DAY
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
            1, "Best friends' chat", today,
            mutableListOf(
                Message(
                    ++messageIdx,
                    1,
                    users[0],
                    today - 12 * MINUTE,
                    TextContent("Hi Jimmy, howdy?")
                ),
                Message(
                    ++messageIdx,
                    1,
                    users[0],
                    today - 2L * MINUTE,
                    TextContent("Wanna go coffee?")
                ),
                Message(++messageIdx, 1, users[1], today, TextContent("Hey dude, sure!")),
            )
        ),
        Chat(
            2, "New movie", yesterday, mutableListOf(
                Message(
                    ++messageIdx,
                    2,
                    users[2],
                    yesterday - 2L * HOUR - 30L * MINUTE,
                    TextContent("Keanu, have you read the script?")
                ),
                Message(
                    ++messageIdx,
                    2,
                    users[2],
                    yesterday - 2 * HOUR,
                    TextContent("Hey, answer me")
                ),
                Message(
                    ++messageIdx,
                    2,
                    users[2],
                    yesterday - HOUR - 10 * MINUTE,
                    TextContent("I'm waiting...")
                ),
                Message(
                    ++messageIdx,
                    2,
                    users[0],
                    yesterday,
                    TextContent("Nope yet, I will, I promise")
                ),
            )
        ),
        Chat(
            3, "Oscar winner", today - 2 * DAY, mutableListOf(
                Message(
                    ++messageIdx,
                    3,
                    users[0],
                    today - 10 * DAY,
                    TextContent("Who will win?")
                ),
                Message(
                    ++messageIdx,
                    3,
                    users[1],
                    today - 8 * DAY - 39 * MINUTE,
                    TextContent("Not you! )")
                ),
                Message(
                    ++messageIdx,
                    3,
                    users[1],
                    today - 4 * DAY - 8 * MINUTE,
                    TextContent("Me? )")
                ),
                Message(
                    ++messageIdx,
                    3,
                    users[0],
                    today - 2 * DAY - 7 * MINUTE,
                    TextContent("Me? )")
                ),
                Message(
                    ++messageIdx,
                    3,
                    users[2],
                    today - 2 * DAY - 6 * MINUTE,
                    TextContent("Me? )")
                ),
                Message(++messageIdx, 3, users[1], today - 2 * DAY - 5 * MINUTE, TextContent("No")),
                Message(
                    ++messageIdx,
                    3,
                    users[1],
                    today - 2 * DAY - 4 * MINUTE,
                    TextContent("Ha-Ha")
                ),
                Message(
                    ++messageIdx,
                    3,
                    users[1],
                    today - 2 * DAY - 3 * MINUTE,
                    TextContent("Choke")
                ),
                Message(
                    ++messageIdx,
                    3,
                    users[1],
                    today - 2 * DAY - 2 * MINUTE,
                    TextContent("Catch me")
                ),
                Message(
                    ++messageIdx,
                    3,
                    users[1],
                    today - 25 * MINUTE,
                    TextContent("Lol hiwnd  jjd rldk gdsrk jhdkj hdkjr dkjh gldkrjh kdrjsh lksjdhglkdrsjdlkjdlrjgdlrsjgdlrsgjhdlrskjgdsgjhrlkjdrj gdsr gd  sdkjrhg kdrsh gkjdrskdsrkhgdsk rkgh  rd")
                ),
            )
        ),
        Chat(4, "Need work", today - 12 * DAY, mutableListOf()),
        Chat(5, "Let's poker today", today - 13 * DAY, mutableListOf()),
        Chat(6, "Billy's birthday", today - 15 * DAY, mutableListOf()),
        Chat(7, "Holidays", today - 20 * DAY, mutableListOf()),
        Chat(8, "Macy's", today - 21 * DAY, mutableListOf()),
        Chat(9, "Mac M1, will you buy it?", today - 30 * DAY, mutableListOf()),
        Chat(10, "Tesla", today - 32 * DAY, mutableListOf()),
        Chat(11, "Mom's club", today - 34 * DAY, mutableListOf()),
        Chat(12, "Tom's birthday", today - 34 * DAY - 4 * HOUR, mutableListOf()),
        Chat(13, "Biden? really?", today - 35 * DAY, mutableListOf()),
        Chat(14, "Car broken, find repair", today - 36 * DAY, mutableListOf()),
        Chat(15, "Kids presents", today - 37 * DAY, mutableListOf()),
        Chat(16, "Lockdown again (", today - 38 * DAY, mutableListOf()),
        Chat(17, "Father's day", today - 39 * DAY, mutableListOf()),
        Chat(18, "They know about it..", today - 40 * DAY, mutableListOf()),
        Chat(19, "Jetpack AMA", today - 40 * DAY, mutableListOf()),
        Chat(20, "iOS or Android?", today - 40 * DAY, mutableListOf()),
        Chat(21, "Experiment ", today - 40 * DAY, mutableListOf()),
        Chat(22, "NASA rover where to find it?", today - 40 * DAY, mutableListOf()),
        Chat(23, "Wages for living..", today - 40 * DAY, mutableListOf()),
        Chat(24, "Best mortgage", today - 45 * DAY, mutableListOf()),
        Chat(25, "Nintendo switch pro? when? prices?", today - 100 * DAY, mutableListOf()),
        Chat(26, "Condo subleasing", today - 140 * DAY, mutableListOf()),
        Chat(27, "Garage sale, friday, 10 am", today - YEAR, mutableListOf()),
        Chat(28, "st. Louise health dept", today - YEAR - 40 * DAY, mutableListOf()),
    )
    private val chatsPages = listOf(
        chats.subList(0, 9),
        chats.subList(10, 19),
        chats.subList(20, chats.size),
    )

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

    override suspend fun getChats(page: Int): List<Chat> {
        Log.d("MockRemoteDataSource", "page: $page")
        delay(delayMs)
        return if (page < chatsPages.size) {
            chatsPages[page]
        } else {
            emptyList()
        }
    }
}