package com.example.composetest.model

data class Message(
    val id: Long,
    val chatId: Long,
    val user: User,
    val time: Long,
    val content: Content,
    val reactions: List<Reaction>
)
