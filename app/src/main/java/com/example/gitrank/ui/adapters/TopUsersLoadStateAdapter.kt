package com.example.gitrank.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrank.databinding.TopUsersLoadStateFooterBinding

class TopUsersLoadStateAdapter(private val retry: () -> Unit ) :
  LoadStateAdapter<TopUsersLoadStateAdapter.LoadStateViewHolder>() {

  override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
    holder.bind(loadState)
  }

  override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
    val binding = TopUsersLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context),
    parent,
    false)
    return LoadStateViewHolder(binding)
  }

  inner class LoadStateViewHolder(private val binding: TopUsersLoadStateFooterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
      binding.btnRetry.setOnClickListener{
        retry.invoke()
      }
    }

    fun bind(loadState: LoadState) {
      binding.apply {
        progress.isVisible = loadState is LoadState.Loading
        btnRetry.isVisible = loadState !is LoadState.Loading
        error.isVisible = loadState !is LoadState.Loading
      }
    }
  }
}