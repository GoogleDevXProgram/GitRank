package com.example.gitrank.repository

import androidx.paging.PagingSource
import com.example.gitrank.data.entities.TopUsers
import com.example.gitrank.remote.GitRankApi
import java.io.IOException
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX = 1

class GitRankPagingSource(
  private val githubApi: GitRankApi,
  private val query: String
): PagingSource<Int, TopUsers>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopUsers> {
    val position = params.key ?: STARTING_PAGE_INDEX

    return try {
      val response = githubApi.getTopUsers(query, position, params.loadSize)
      val topUsers = response.items

      LoadResult.Page(
        data = topUsers,
        prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
        nextKey = if (topUsers.isEmpty()) null else position + 1
      )
    } catch (ioException: IOException) {
      LoadResult.Error(ioException)
    } catch (httpException: HttpException) {
      LoadResult.Error(httpException)
    }
  }

}