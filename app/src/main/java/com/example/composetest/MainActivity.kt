package com.example.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import com.example.composetest.data.MockChatsRepository
import com.example.composetest.data.source.MockRemoteDataSource
import com.example.composetest.ui.chatlist.ChatListScreen
import com.example.composetest.ui.theme.ComposeTestTheme

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ChatListViewModel
    private val repository = MockChatsRepository(MockRemoteDataSource())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ChatListViewModel.Factory(repository)
        ).get(ChatListViewModel::class.java)

        setContent {
            ComposeTestTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ChatListScreen()
                }
            }
        }
    }
}

