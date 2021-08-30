package com.example.gitrank.data.entities

import com.google.gson.annotations.SerializedName

/**
 * Immutable model class for a Github user that holds all the information about a user.
 * Objects of this type are received from the Github API, therefore all the fields are annotated
 * with the serialized name.
 * This class also defines the Room repos table, where the user [id] is the primary key.
 */
data class TopUser(
  @SerializedName("id") val id: Long,
  @SerializedName("login") val userName: String,
  @SerializedName("avatar_url") val avatarUrl: String,
  @SerializedName("url") val url: String
)
