package com.example.composetest

import androidx.lifecycle.*
import com.example.composetest.model.Chat
import com.example.composetest.data.ChatsRepository
import com.example.composetest.data.MockChatsRepository
import com.example.composetest.data.source.MockRemoteDataSource
import com.example.composetest.model.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatListViewModel() : ViewModel() {
    private var repository: ChatsRepository = MockChatsRepository(MockRemoteDataSource(1500))

    private val _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>> = _chats

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    private val _chat = MutableLiveData<Chat>()
    val chat: LiveData<Chat> = _chat

    fun fetchAllChats() {
        viewModelScope.launch(Dispatchers.IO) {
            _chats.postValue(repository.getAllChats())
        }
    }

    fun fetchMessages(chatId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _messages.postValue(repository.getChatMessages(chatId))
        }
    }

    fun fetchChatInfo(chatId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _chat.postValue(repository.fetchChatInfo(chatId))
        }
    }
}