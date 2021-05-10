package com.example.gitrank.data.entities

/**
 * [TopUsers] data class can also serve an entity class for our db,
 * the parameters here are for test only to build out the ui and can be refactored later
 * to match our API endpoints
 */
data class TopUsers(
  val id: String,
  val githubUsername: String,
  val followers: String,
  val avatarUrl: Int
)