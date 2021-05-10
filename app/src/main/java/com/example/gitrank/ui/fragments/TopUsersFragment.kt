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
import com.example.gitrank.data.entities.TopUsers
import com.example.gitrank.databinding.FragmentTopUsersBinding
import com.example.gitrank.ui.adapters.TopUsersAdapter


class TopUsersFragment : Fragment() {
  lateinit var navController: NavController
  lateinit var  usersBinding: FragmentTopUsersBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    usersBinding = FragmentTopUsersBinding.inflate(layoutInflater, container, false)
    return usersBinding.root
  }

  override fun onViewCreated(view : View, savedInstanceState : Bundle?){
    super.onViewCreated(view, savedInstanceState)
    navController = Navigation.findNavController(view)

    // add dummy data to recyclerview
    setRecyclerData()
  }

  private fun setRecyclerData() {
    /**
     * a list of dummy top users for testing out the recyclerview
     * should be deleted after integrating remote data
     */
    val dummyTopUsersList: ArrayList<TopUsers> = ArrayList()
    dummyTopUsersList.apply {
      add(
        TopUsers(
          "2",
          "ugwulo",
          "16",
          R.drawable.sample_avatar
        )
      )
      add(
        TopUsers(
          "1",
          "jumaallan",
          "1206",
          R.drawable.sample_avatar
        )
      )
      add(
        TopUsers(
          "3",
          "Mikail",
          "123406",
          R.drawable.sample_avatar
        )
      )
    }

    val topUsersAdapter = TopUsersAdapter(dummyTopUsersList)
    val linearLayoutManager = LinearLayoutManager(requireContext())
    usersBinding.rvTopUsers.apply {
      adapter = topUsersAdapter
      layoutManager = linearLayoutManager
    }
  }
}