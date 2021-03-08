package com.example.composetest.ui.chatlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetest.ChatListViewModel
import com.example.composetest.model.Chat
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT_FULL = "d MMM YYYY"
const val DATE_FORMAT_YEAR = "d MMM"
const val DATE_FORMAT_WEEK = "EEEE"
const val DATE_FORMAT_TODAY = "hh:mm"
const val DAY = 1000 * 60 * 60 * 24L
const val WEEK = DAY * 7L
const val YEAR = DAY * 365L


@Composable
fun ChatListScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Chats") }) },
        content = {
            ChatsList()
        })
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
    val timeString = remember(chat.lastUpdated) { formatDate(chat.lastUpdated) }

    Column() {
        ConstraintLayout(modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()) {
            val (title, time) = createRefs()
            Text(
                text = chat.name,
                modifier = Modifier.constrainAs(title) { start.linkTo(parent.start) }
            )
            Text(text = timeString,
                modifier = Modifier.constrainAs(time) { end.linkTo(parent.end) }
            )
        }
        Divider()
    }
}

fun formatDate(timeStamp: Long): String {
    val today = Calendar.getInstance()
    val chatDate = Date(timeStamp * 1000)
    val diff = today.timeInMillis  - timeStamp * 1000
    return when {
        diff >= YEAR -> {
            val dateFormat = SimpleDateFormat(DATE_FORMAT_FULL, Locale.getDefault())
            dateFormat.format(chatDate)
        }
        diff >= WEEK -> {
            val dateFormat = SimpleDateFormat(DATE_FORMAT_YEAR, Locale.getDefault())
            dateFormat.format(chatDate)
        }
        diff >= DAY -> {
            val dateFormat = SimpleDateFormat(DATE_FORMAT_WEEK, Locale.getDefault())
            dateFormat.format(chatDate)
        }
        else -> {
            val dateFormat = SimpleDateFormat(DATE_FORMAT_TODAY, Locale.getDefault())
            dateFormat.format(chatDate)
        }
    }
}