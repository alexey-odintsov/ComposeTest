package com.example.composetest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composetest.model.Chat
import com.example.composetest.data.ChatsRepository
import com.example.composetest.data.MockChatsRepository
import com.example.composetest.data.source.MockRemoteDataSource
import com.example.composetest.model.Message

class ChatListViewModel() : ViewModel() {
    private var repository: ChatsRepository = MockChatsRepository(MockRemoteDataSource())
    private val _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>> = _chats

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    fun fetchAllChats() {
        _chats.value = repository.getAllChats()
    }

    fun fetchMessages(chatId: Long) {
        _messages.value = repository.getChatMessages(chatId)
    }

    fun getMessages(chatId: Long): List<Message> {
        return repository.getChatMessages(chatId)
    }

    fun fetchChatInfo(chatId: Long): Chat? {
        return repository.fetchChatInfo(chatId)
    }

//    class Factory(private val repository: ChatsRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(ChatListViewModel::class.java)) {
//                return ChatListViewModel(repository) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//
//    }
}