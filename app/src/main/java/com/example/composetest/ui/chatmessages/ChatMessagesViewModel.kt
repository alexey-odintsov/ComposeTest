package com.example.composetest.ui.chatmessages

import android.util.Log
import androidx.lifecycle.*
import com.example.composetest.RepositoryProvider
import com.example.composetest.Resource
import com.example.composetest.model.Chat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatMessagesViewModel(chatId: Long, repositoryProvider: RepositoryProvider) : ViewModel() {
    private var repository = repositoryProvider.getRepository()

    private val _chat = MutableLiveData<Resource<Chat>>()
    val chat: LiveData<Resource<Chat>> = _chat

    init {
        fetchChatInfo(chatId)
    }

    private fun fetchChatInfo(chatId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _chat.postValue(Resource.success(repository.fetchChatInfo(chatId)))
        }
    }

    fun sendMessage(userId: Int, chatId: Long, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendMessage(userId, chatId, text)
            Log.d("ChatMessagesViewModel", "sendMessage: $userId $chatId $text")
            _chat.postValue(Resource.loading(null))
            fetchChatInfo(chatId)
        }
    }

    class Factory(private val chatId: Long, private val application: RepositoryProvider) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChatMessagesViewModel::class.java)) {
                return ChatMessagesViewModel(chatId, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}