package com.example.gitrank.remote

import com.example.gitrank.data.entities.TopUsers
import com.google.gson.annotations.SerializedName

data class TopUserSearchResponse (
  @SerializedName("total_count")
  val total: Int = 0,

  @SerializedName("items")
  val items: List<TopUsers> = emptyList(),

  val nextPage: Int? = null
)