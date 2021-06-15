package com.example.gitrank.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.gitrank.repository.GitRankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopUsersViewModel @Inject constructor(
  private val repository: GitRankRepository
  ) : ViewModel() {

  private val currentQuery = MutableLiveData(DEFAULT_QUERY)

  val topUsers = currentQuery.switchMap { queryString ->
    repository.getTopUserSearchResults(queryString).cachedIn(viewModelScope)
  }

  fun searchUsers(query: String) {
    currentQuery.value = query
  }

  companion object {
    private const val DEFAULT_QUERY = "followers:>=1000"
  }

  }