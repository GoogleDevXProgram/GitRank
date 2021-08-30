package com.example.gitrank.ui.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrank.databinding.FragmentTopUsersBinding
import com.example.gitrank.ui.adapters.TopUsersAdapter
import com.example.gitrank.ui.viewmodel.TopUsersViewModel
import com.example.gitrank.ui.viewmodel.UiAction
import com.example.gitrank.ui.viewmodel.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_top_users.rv_top_users
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopUsersFragment : Fragment() {
  lateinit var navController: NavController
  lateinit var  binding: FragmentTopUsersBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentTopUsersBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view : View, savedInstanceState : Bundle?){
    super.onViewCreated(view, savedInstanceState)
    navController = Navigation.findNavController(view)

    // get the view model
    val viewModel: TopUsersViewModel by viewModels()



    // bind the state
    binding.bindState(
      uiState = viewModel.state,
      uiActions = viewModel.accept
    )
  }

  /**s
   * Binds the [UiState] provided  by the [TopUsersViewModel] to the UI,
   * and allows the UI to feed back user actions to it.
   */
  private fun FragmentTopUsersBinding.bindState(
    uiState: StateFlow<UiState>,
    uiActions: (UiAction) -> Unit
  ) {
    val topUsersAdapter = TopUsersAdapter()
    rv_top_users.adapter = topUsersAdapter

    bindSearch(
      uiState = uiState,
      onQueryChanged = uiActions
    )
    bindList(
      topUsersAdapter = topUsersAdapter,
      uiState = uiState,
      onScrollChanged = uiActions
    )
  }

  private fun FragmentTopUsersBinding.bindSearch(
    uiState: StateFlow<UiState>,
    onQueryChanged: (UiAction.Search) -> Unit
  ) {
    searchTopUser.setOnEditorActionListener { _, actionId, _ ->
      if (actionId == EditorInfo.IME_ACTION_GO) {
        updateRepoListFromInput(onQueryChanged)
        true
      } else {
        false
      }
    }
    searchTopUser.setOnKeyListener { _, keyCode, event ->
      if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
        updateRepoListFromInput(onQueryChanged)
        true
      } else {
        false
      }
    }

    lifecycleScope.launch {
      uiState
        .map { it.query }
        .distinctUntilChanged()
        .collect(searchTopUser::setText)
    }
  }

  private fun FragmentTopUsersBinding.updateRepoListFromInput(onQueryChanged: (UiAction.Search) -> Unit) {
    searchTopUser.text.trim().let {
      if (it.isNotEmpty()) {
        rv_top_users.scrollToPosition(0)
        onQueryChanged(UiAction.Search(query = it.toString()))
      }
    }
  }

  private fun FragmentTopUsersBinding.bindList(
    topUsersAdapter: TopUsersAdapter,
    uiState: StateFlow<UiState>,
    onScrollChanged: (UiAction.Scroll) -> Unit
  ) {
    rv_top_users.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy != 0) onScrollChanged(UiAction.Scroll(currentQuery = uiState.value.query))
      }
    })
    val notLoading = topUsersAdapter.loadStateFlow
      // Only emit when REFRESH LoadState for RemoteMediator changes.
      .distinctUntilChangedBy { it.refresh }
      // Only react to cases where Remote REFRESH completes i.e., NotLoading.
      .map { it.refresh is LoadState.NotLoading }

    val hasNotScrolledForCurrentSearch = uiState
      .map { it.hasNotScrolledForCurrentSearch }
      .distinctUntilChanged()

    val shouldScrollToTop = combine(
      notLoading,
      hasNotScrolledForCurrentSearch,
      Boolean::and
    )
      .distinctUntilChanged()

    val pagingData = uiState
      .map { it.pagingData }
      .distinctUntilChanged()

    lifecycleScope.launch {
      combine(shouldScrollToTop, pagingData, ::Pair)
        // Each unique PagingData should be submitted once, take the latest from
        // shouldScrollToTop
        .distinctUntilChangedBy { it.second }
        .collectLatest { (shouldScroll, pagingData) ->
          topUsersAdapter.submitData(pagingData)
          // Scroll only after the data has been submitted to the adapter,
          // and is a fresh search
          if (shouldScroll) rv_top_users.scrollToPosition(0)
        }
    }
  }

}