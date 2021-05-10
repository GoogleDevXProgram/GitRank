package com.example.gitrank.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.gitrank.databinding.FragmentRepositoriesBinding


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
  }

}