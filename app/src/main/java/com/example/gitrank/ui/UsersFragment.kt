package com.example.gitrank.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.gitrank.databinding.FragmentUsersBinding


class UsersFragment : Fragment() {
  lateinit var navController: NavController
  lateinit var  usersBinding: FragmentUsersBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    usersBinding = FragmentUsersBinding.inflate(layoutInflater, container, false)
    return usersBinding.root
  }

  override fun onViewCreated(view : View, savedInstanceState : Bundle?){
    super.onViewCreated(view, savedInstanceState)
    navController = Navigation.findNavController(view)
  }

}