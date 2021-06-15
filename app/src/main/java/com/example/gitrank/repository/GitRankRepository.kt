package com.example.gitrank.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.gitrank.remote.GitRankApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitRankRepository @Inject constructor(private val gitRankApi: GitRankApi) {

  fun getTopUserSearchResults(query: String) =
    Pager(
      config = PagingConfig(
        pageSize = 20,
        maxSize = 100,
        enablePlaceholders = false
      ),
      pagingSourceFactory = { GitRankPagingSource(gitRankApi, query) }
    ).liveData
}