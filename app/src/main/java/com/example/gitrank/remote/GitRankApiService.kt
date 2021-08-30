package com.example.gitrank.remote


import retrofit2.http.GET
import retrofit2.http.Query

const val IN_QUALIFIER = "in:name,description"

interface GitRankApiService {

  /**
   * Get users ordered by followers.
   */
  @GET("search/users?sort=followers")
  suspend fun searchTopUsers(
    @Query("q") query: String,
    @Query("page") page: Int,
    @Query("per_page") itemsPerPage: Int
  ): TopUserSearchResponse

//  @GET("get")
//  suspend fun getTopUsers(
//    @Query("id") id: Int
//  ): RecipeDto
}

