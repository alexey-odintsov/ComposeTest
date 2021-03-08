package com.example.composetest.ui

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.composetest.ChatListViewModel
import com.example.composetest.formatDate
import com.example.composetest.model.*
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ChatMessagesScreen(navController: NavHostController, chatId: Long) {
    val viewModel: ChatListViewModel = viewModel()
    viewModel.fetchChatInfo(chatId)
    viewModel.fetchMessages(chatId)
    val messages: List<Message> by viewModel.messages.observeAsState(listOf())
    val chat: Chat? by viewModel.chat.observeAsState(null)
//    val chat = remember("chat:$chatId") { viewModel.fetchChatInfo(chatId) }
//    val messages = remember(chatId) { viewModel.getMessages(chatId) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("${chat?.name}") }) },
        content = {
            if (messages.isEmpty()) {
                EmptyChat()
            } else {
                ChatsList(messages)
            }
        })
}

@Composable
fun EmptyChat() {
    Text(text = "There is no messages yet.")
}

@Composable
fun ChatsList(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            ChatMessage(message)
        }
    }
}

@Preview
@Composable
fun PreviewMessage() {
    ChatMessage(
        message = Message(
            12348,
            2,
            User(
                1,
                "User name",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjMp1kB4QaKOtXChZshSYDhxyuUaVbq8swiQ&usqp=CAU"
            ),
            1612697554L,
            TextContent("Keanu, have you read the script?")
        ),
    )
}

@Composable
fun ChatMessage(message: Message) {
    val timeString = remember(message.id) { formatDate(message.time) }

    ConstraintLayout(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        val (_avatar, _name, _time, _message) = createRefs()

        CoilImage(
            data = message.user.avatar,
            contentDescription = message.user.name,
            modifier = Modifier
                .constrainAs(_avatar) { start.linkTo(parent.start) }
                .border(4.dp, Color.Gray, CircleShape)
                .padding(2.dp)
                .clip(CircleShape)
                .size(40.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = message.user.name,
            modifier = Modifier.constrainAs(_name) { start.linkTo(_avatar.end, 8.dp) },
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Text(
            text = timeString,
            modifier = Modifier.constrainAs(_time) { end.linkTo(parent.end) }
        )
        Content(
            modifier = Modifier.constrainAs(_message) {
                start.linkTo(_name.start)
                top.linkTo(_name.bottom, 8.dp)
            },
            content = message.content
        )
    }
}

@Composable
fun Content(modifier: Modifier, content: Content) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        color = Color(0xe0, 0xe0, 0xe0)
    ) {
        when (content.type) {
            ContentType.TEXT -> Text(
                modifier = Modifier.padding(8.dp),
                text = (content as TextContent).value
            )
            ContentType.IMAGE -> CoilImage(
                modifier = Modifier.padding(8.dp),
                data = (content as ImageContent).value,
                contentDescription = null
            )
            ContentType.STICKER -> CoilImage(
                modifier = Modifier.padding(8.dp),
                data = (content as StickerContent).value,
                contentDescription = null
            )
        }
    }
}