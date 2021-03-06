package com.example.composetest.ui.chatlist

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.composetest.RepositoryProvider
import com.example.composetest.Screens
import com.example.composetest.formatDate
import com.example.composetest.model.Chat
import com.example.composetest.ui.common.LoadingProgress

@Composable
fun ChatListScreen(navController: NavHostController, repositoryProvider: RepositoryProvider) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Chats") }) },
        content = {
            ChatsList(navController, repositoryProvider)
        })
}

@Composable
fun ChatsList(navController: NavHostController, repositoryProvider: RepositoryProvider) {
    val viewModel: ChatListViewModel =
        viewModel(factory = ChatListViewModel.Factory(repositoryProvider))

    val lazyPagingItems: LazyPagingItems<Chat> =
        viewModel.chatsFlow.collectAsLazyPagingItems()

    LazyColumn {
        itemsIndexed(lazyPagingItems) { page, chat ->
            if (chat != null) {
                ChatItem(chat, navController)
            }
        }
        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> item {
                    LoadingProgress()
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingProgress() }
                }
            }
        }
    }
}

@Composable
fun ChatItem(chat: Chat, navController: NavHostController) {
    Log.d("ChatMessage", "call for ${chat.name}")

    val timeString = remember(chat.lastUpdated) { formatDate(chat.lastUpdated) }

    Column(modifier = Modifier.clickable { navController.navigate("${Screens.CHAT_MESSAGES}/${chat.id}") }) {
        ConstraintLayout(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 20.dp)
                .fillMaxWidth()
        ) {
            val (title, time) = createRefs()
            Text(
                text = chat.name,
                modifier = Modifier.constrainAs(title) { start.linkTo(parent.start) }
            )
            Text(
                text = timeString,
                modifier = Modifier.constrainAs(time) { end.linkTo(parent.end) }
            )
        }
        Divider()
    }
}