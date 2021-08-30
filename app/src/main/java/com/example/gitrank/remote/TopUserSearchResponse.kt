package com.example.gitrank.remote

import com.example.gitrank.data.entities.TopUser
import com.google.gson.annotations.SerializedName

/**
 * Data class to hold TopUser responses from searchTopUser API calls.
 */
data class TopUserSearchResponse (
  @SerializedName("total_count") val total: Int = 0,
  @SerializedName("items") val items: List<TopUser> = emptyList(),
  val nextPage: Int? = null
)