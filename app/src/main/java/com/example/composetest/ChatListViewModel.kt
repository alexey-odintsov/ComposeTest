package com.example.composetest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composetest.data.Chat
import com.example.composetest.data.ChatsRepository

class ChatListViewModel(private val repository: ChatsRepository) : ViewModel() {
    private val _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>> = _chats

    init {
        fetchAllChats()
    }

    private fun fetchAllChats() {
        _chats.value = repository.getAllChats()
    }

    class Factory(private val repository: ChatsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChatListViewModel::class.java)) {
                return ChatListViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}