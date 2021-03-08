package com.example.composetest.ui.chatlist

import android.util.Log
import androidx.lifecycle.*
import com.example.composetest.RepositoryProvider
import com.example.composetest.Resource
import com.example.composetest.model.Chat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatListViewModel(repositoryProvider: RepositoryProvider) : ViewModel() {
    private var repository = repositoryProvider.getRepository()

    private val _chats = MutableLiveData<Resource<List<Chat>>>()
    val chats: LiveData<Resource<List<Chat>>> = _chats

    init {
        fetchAllChats()
    }

    private fun fetchAllChats() {
        Log.d("MainViewModel", "fetchAllChats")
        _chats.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            _chats.postValue(Resource.success(repository.getAllChats()))
        }
    }

    class Factory(private val repositoryProvider: RepositoryProvider) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChatListViewModel::class.java)) {
                return ChatListViewModel(repositoryProvider) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}