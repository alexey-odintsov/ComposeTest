package com.example.composetest.ui.chatlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetest.ChatListViewModel
import com.example.composetest.data.Chat

@Composable
fun ChatListScreen() {
    ChatsList()
}

@Preview
@Composable
fun ChatsList() {
    val viewModel: ChatListViewModel = viewModel()
    val chats: List<Chat> by viewModel.chats.observeAsState(listOf())

    LazyColumn {
        items(chats) { chat ->
            ChatItem(chat)
        }
    }
}

@Composable
fun ChatItem(chat: Chat) {
    Column {
        Row {
            Text(text = chat.name)
            Text(text = chat.lastUpdated.toString())
        }
        Divider()
    }
}