package com.example.composetest.model

data class Chat(
    val id: Long,
    var name: String,
    var lastUpdated: Long,
    var messages: List<Message>
)