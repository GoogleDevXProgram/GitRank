package com.example.gitrank.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitrank.data.entities.Repositories
import com.example.gitrank.databinding.ListItemRepositoriesBinding

class RepositoriesAdapter(private val listRepositories: ArrayList<Repositories>) :
  RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder =
    RepositoriesViewHolder(ListItemRepositoriesBinding.inflate(
      LayoutInflater.from(parent.context),
      parent,
      false
    ))

  override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) =
    holder.bind(listRepositories[position])

  override fun getItemCount(): Int = listRepositories.size

  class RepositoriesViewHolder(val binding: ListItemRepositoriesBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(repositories: Repositories) {
      binding.ownerName.text = repositories.ownerName
      binding.repositoryStars.text = repositories.stars
      binding.ranking.text = repositories.rank
      binding.repositoryName.text = repositories.repositoryName

      Glide.with(binding.repositoryAvatar.context)
        .load(repositories.avatarUrl)
        .centerCrop()
        .into(binding.repositoryAvatar)
    }
  }
}