package com.example.gitrank.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitrank.data.entities.TopUsers
import com.example.gitrank.databinding.ListItemTopUsersBinding

class TopUsersAdapter(private val topUsersList: ArrayList<TopUsers>) :
  RecyclerView.Adapter<TopUsersAdapter.TopUsersViewHolder>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopUsersViewHolder =
    TopUsersViewHolder(
      ListItemTopUsersBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false)
    )

  override fun onBindViewHolder(holder: TopUsersViewHolder, position: Int) =
    holder.bind(topUsersList[position])

  override fun getItemCount(): Int = topUsersList.size

  class TopUsersViewHolder(private val binding: ListItemTopUsersBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(topUsers: TopUsers) {
      binding.githubUserName.text = topUsers.githubUsername
      binding.ranking.text = topUsers.rank
      binding.userFollowers.text = topUsers.followers

      Glide.with(binding.userProfileAvatar.context)
        .load(topUsers.avatarUrl)
        .centerCrop()
        .into(binding.userProfileAvatar)
    }
  }
}