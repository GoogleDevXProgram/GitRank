package com.example.gitrank.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gitrank.data.entities.TopUser
import com.example.gitrank.remote.GitRankApiService
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class GitRankRepository @Inject constructor(private val service: GitRankApiService) {

  fun getSearchResultStream(query: String): Flow<PagingData<TopUser>> {
    return Pager(
      config = PagingConfig(
        pageSize = NETWORK_PAGE_SIZE,
        enablePlaceholders = false
      ),
      pagingSourceFactory = { GitRankPagingSource(service, query) }
    ).flow
  }

  companion object {
    const val NETWORK_PAGE_SIZE = 50
  }
}