package com.example.gitrank.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitrank.R
import com.example.gitrank.data.entities.Repositories
import com.example.gitrank.databinding.FragmentRepositoriesBinding
import com.example.gitrank.ui.adapters.RepositoriesAdapter
import com.example.gitrank.ui.adapters.TopUsersAdapter


class RepositoriesFragment : Fragment() {
  lateinit var navController: NavController
  lateinit var  repositoriesBinding: FragmentRepositoriesBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    repositoriesBinding = FragmentRepositoriesBinding.inflate(layoutInflater, container, false)
    return repositoriesBinding.root
  }

  override fun onViewCreated(view : View, savedInstanceState : Bundle?){
    super.onViewCreated(view, savedInstanceState)
    navController = Navigation.findNavController(view)

    // add dummy data to recyclerview
    setRecyclerData()
  }

  private fun setRecyclerData() {
    /**
     * a list of dummy top repositories for testing out the recyclerview
     * should be deleted after integrating remote data
     */
    val dummyRepositoryList: ArrayList<Repositories> = ArrayList()
    dummyRepositoryList.apply {
      add(
        Repositories(
          "1",
          "ugwulo",
          "GitRank",
          "40",
          R.drawable.sample_avatar
        )
      )
      add(
        Repositories(
          "2",
          "Mikail",
          "Lorem",
          "400",
          R.drawable.sample_avatar
        )
      )
      add(
        Repositories(
          "3",
          "jumaallan",
          "LoremIpsumDolor",
          "40000",
          R.drawable.sample_avatar
        )
      )
    }

    val topRepositories = RepositoriesAdapter(dummyRepositoryList)
    val linearLayoutManager = LinearLayoutManager(requireContext())
    repositoriesBinding.rvRepositories.apply {
      adapter = topRepositories
      layoutManager = linearLayoutManager
    }
  }
}