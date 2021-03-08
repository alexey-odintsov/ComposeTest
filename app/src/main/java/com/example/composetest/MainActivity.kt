package com.example.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.composetest.ui.chatlist.ChatListViewModel
import com.example.composetest.ui.theme.ComposeTestTheme

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ChatListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavGraph(repositoryProvider = (application as App) as RepositoryProvider)
                }
            }
        }
    }
}

