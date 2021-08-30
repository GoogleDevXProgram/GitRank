package com.example.gitrank.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gitrank.data.entities.TopUser
import com.example.gitrank.remote.GitRankApiService
import com.example.gitrank.remote.IN_QUALIFIER
import java.io.IOException
import retrofit2.HttpException

// GitHub page API is 1 based: https://developer.github.com/v3/#pagination
private const val GITHUB_STARTING_PAGE_INDEX = 1

class GitRankPagingSource(
  private val service: GitRankApiService,
  private val query: String
) : PagingSource<Int, TopUser>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopUser> {
    val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
    val apiQuery = query + IN_QUALIFIER
    return try {
      val response = service.searchTopUsers(apiQuery, position, params.loadSize)
      val topUsers = response.items
      val nextKey = if (topUsers.isEmpty()) {
        null
      } else {
        // initial load size = 3 * NETWORK_PAGE_SIZE
        // ensure we're not requesting duplicating items, at the 2nd request
        position + (params.loadSize / NETWORK_PAGE_SIZE)
      }
      LoadResult.Page(
        data = topUsers,
        prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
        nextKey = nextKey
      )
    } catch (exception: IOException) {
      return LoadResult.Error(exception)
    } catch (exception: HttpException) {
      return LoadResult.Error(exception)
    }
  }
  // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
  override fun getRefreshKey(state: PagingState<Int, TopUser>): Int? {
    // We need to get the previous key (or next key if previous is null) of the page
    // that was closest to the most recently accessed index.
    // Anchor position is the most recently accessed index
    return state.anchorPosition?.let { anchorPosition ->
      state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
  }

  companion object {
    const val NETWORK_PAGE_SIZE = 30
  }
}

