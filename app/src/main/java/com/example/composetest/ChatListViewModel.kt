package com.example.composetest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composetest.model.Chat
import com.example.composetest.data.ChatsRepository
import com.example.composetest.model.Message

class ChatListViewModel(private val repository: ChatsRepository) : ViewModel() {
    private val _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>> = _chats

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    init {
        fetchAllChats()
    }

    public fun fetchAllChats() {
        _chats.value = repository.getAllChats()
    }

    public fun fetchMessages(chatId: Long) {
        _messages.value = repository.getChatMessages(chatId)
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