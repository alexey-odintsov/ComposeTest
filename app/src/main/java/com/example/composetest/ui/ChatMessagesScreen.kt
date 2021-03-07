package com.example.composetest.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetest.ChatListViewModel
import com.example.composetest.model.ImageContent
import com.example.composetest.model.Message
import com.example.composetest.model.TextContent
import com.example.composetest.model.User
import dev.chrisbanes.accompanist.coil.CoilImage

@Preview
@Composable
fun ChatMessagesScreen() {
    val viewModel: ChatListViewModel = viewModel()
    val messages: List<Message> by viewModel.messages.observeAsState(listOf())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Chats") }) },
        content = {
            ChatsList(messages)
        })
}

@Composable
fun ChatsList(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            ChatMessage(message)
        }
    }
}

@Composable
fun ChatMessage(message: Message) {
    UserAvatar(message.user)
    when (message.content) {
        is TextContent -> Text(message.content.value)
        is ImageContent -> CoilImage(data = message.content.value, contentDescription = null)
    }
}

@Composable
fun UserAvatar(user: User) {
    CoilImage(
        data = user.avatar,
        contentDescription = user.name,
        modifier = Modifier
            .height(120.dp)
            .border(2.dp, Color.Gray, CircleShape)
            .clip(CircleShape)
    )
}