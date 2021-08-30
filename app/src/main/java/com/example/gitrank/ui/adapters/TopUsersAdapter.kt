package com.example.gitrank.ui.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitrank.R
import com.example.gitrank.data.entities.TopUser
import com.example.gitrank.databinding.ListItemTopUsersBinding

class TopUsersAdapter() :
  PagingDataAdapter<TopUser, TopUsersAdapter.TopUsersViewHolder>(TOP_USER_COMPARATOR)  {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopUsersViewHolder =
    TopUsersViewHolder(
      ListItemTopUsersBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false)
    )

  override fun onBindViewHolder(holder: TopUsersViewHolder, position: Int) {
    val topUserItem = getItem(position)
    if (topUserItem != null) {
      holder.bind(topUserItem)
    }
  }

  companion object {
    private val TOP_USER_COMPARATOR = object : DiffUtil.ItemCallback<TopUser>() {
      override fun areItemsTheSame(oldItem: TopUser, newItem: TopUser): Boolean =
        oldItem.userName == newItem.userName

      override fun areContentsTheSame(oldItem: TopUser, newItem: TopUser): Boolean =
        oldItem == newItem
    }
  }

  class TopUsersViewHolder(private val binding: ListItemTopUsersBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var topUser: TopUser? = null

    init {
      binding.root.setOnClickListener{
        topUser?.url?.let { url ->
          val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
          binding.root.context.startActivity(intent)
        }
      }
    }

    fun bind(topUser: TopUser?) {
      if (topUser == null) {
        binding.githubUserName.visibility = View.GONE
        binding.ranking.visibility = View.GONE
        binding.userProfileAvatar.visibility = View.GONE
      } else {
        showTopUser(topUser)
      }
    }

    private fun showTopUser(topUser: TopUser) {
      binding.githubUserName.text = topUser.userName
      binding.ranking.text = topUser.id.toString()

      Glide.with(binding.userProfileAvatar.context)
        .load(topUser.avatarUrl)
        .centerCrop()
        .into(binding.userProfileAvatar)
    }
  }
}