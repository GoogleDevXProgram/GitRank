package com.example.gitrank.data.entities
/**
 * [Repositories] data class can also serve an entity class for our db,
 * the parameters here are for test only to build out the ui and can be refactored later
 * to match our API endpoints
 */

data class Repositories(
  val rank: String,
  val ownerName: String,
  val repositoryName: String,
  val stars: String,
  val avatarUrl: Int
)