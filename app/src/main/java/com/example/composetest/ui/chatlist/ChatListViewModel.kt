package com.example.composetest.ui.chatlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.*
import com.example.composetest.RepositoryProvider
import com.example.composetest.data.ChatsRepository
import com.example.composetest.model.Chat
import kotlinx.coroutines.flow.Flow

class ChatListViewModel(repositoryProvider: RepositoryProvider) : ViewModel() {
    private var repository = repositoryProvider.getRepository()

    val chatsFlow: Flow<PagingData<Chat>> = Pager(PagingConfig(pageSize = 10)) {
        ChatsPagingSource(repository)
    }.flow

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

class ChatsPagingSource(private val repository: ChatsRepository) : PagingSource<Int, Chat>() {
    override fun getRefreshKey(state: PagingState<Int, Chat>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Chat> {
        return try {
            val page = params.key ?: 0
            val response = repository.getChats(page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}