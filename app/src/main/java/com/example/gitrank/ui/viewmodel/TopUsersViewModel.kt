package com.example.gitrank.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gitrank.data.entities.TopUser
import com.example.gitrank.repository.GitRankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class TopUsersViewModel @Inject constructor(
  private val repository: GitRankRepository,
  private val savedStateHandle: SavedStateHandle
) : ViewModel() {

  /**
   * Stream of immutable states representative of the UI.
   */
  val state: StateFlow<UiState>

  /**
   * Processor of side effects from the UI which in turn feedback into [state]
   */
  val accept: (UiAction) -> Unit

  override fun onCleared() {
    savedStateHandle[LAST_SEARCH_QUERY] = state.value.query
    savedStateHandle[LAST_QUERY_SCROLLED] = state.value.lastQueryScrolled
    super.onCleared()
  }

  private fun searchTopUser(queryString: String): Flow<PagingData<TopUser>> =
    repository.getSearchResultStream(queryString)
      .cachedIn(viewModelScope)

  init {
    val initialQuery: String = savedStateHandle.get(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
    val lastQueryScrolled: String = savedStateHandle.get(LAST_QUERY_SCROLLED) ?: DEFAULT_QUERY
    val actionStateFlow = MutableSharedFlow<UiAction>()
    val searches = actionStateFlow
      .filterIsInstance<UiAction.Search>()
      .distinctUntilChanged()
    val queriesScrolled = actionStateFlow
      .filterIsInstance<UiAction.Scroll>()
      .distinctUntilChanged()
      // This is shared to keep the flow "hot" while caching the last query scrolled,
      // otherwise each flatMapLatest invocation would lose the last query scrolled,
      .shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        replay = 1
      )
      .onStart { emit(UiAction.Scroll(currentQuery = lastQueryScrolled)) }

    state = searches
      .flatMapLatest { search ->
        combine(
          queriesScrolled,
          searchTopUser(queryString = search.query),
          ::Pair
        )
          // Each unique PagingData should be submitted once, take the latest from
          // queriesScrolled
          .distinctUntilChangedBy { it.second }
          .map { (scroll, pagingData) ->
            UiState(
              query = search.query,
              pagingData = pagingData,
              lastQueryScrolled = scroll.currentQuery,
              // If the search query matches the scroll query, the user has scrolled
              hasNotScrolledForCurrentSearch = search.query != scroll.currentQuery
            )
          }
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = UiState()
      )

    accept = { action ->
      viewModelScope.launch { actionStateFlow.emit(action) }
    }
  }
}


sealed class UiAction {
  data class Search(val query: String) : UiAction()
  data class Scroll(val currentQuery: String) : UiAction()
}

data class UiState(
  val query: String = DEFAULT_QUERY,
  val lastQueryScrolled: String = DEFAULT_QUERY,
  val hasNotScrolledForCurrentSearch: Boolean = false,
  val pagingData: PagingData<TopUser> = PagingData.empty()
)

private const val VISIBLE_THRESHOLD = 5
private const val LAST_SEARCH_QUERY: String = "last_search_query"
private const val DEFAULT_QUERY = "followers:>10000"
private const val LAST_QUERY_SCROLLED: String = "last_query_scrolled"