package com.selva.anime.ui.home

import androidx.lifecycle.viewModelScope
import com.selva.anime.baseutils.Result
import com.selva.anime.domain.usecase.impl.GetAnimeUseCaseImpl
import com.selva.anime.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetAnimeUseCaseImpl
) : BaseViewModel<HomeContract.Effect, HomeContract.Event, HomeContract.State>() {

    private val _navigationEvents = MutableSharedFlow<HomeNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    override fun createInitialState(): HomeContract.State {
        return HomeContract.State(
            HomeContract.AnimeState.Idle
        )
    }

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            HomeContract.Event.ErrorFetching -> {
                setEffect { HomeContract.Effect.ShowToast }
            }

            is HomeContract.Event.OnAnimeSelected -> {
                viewModelScope.launch {
                    _navigationEvents.emit(HomeNavigationEvent.NavigateToAnimeDetails(event.animeItem))
                }
            }
        }
    }

    fun fetchTopAnime() {
        setState { copy(animeState = HomeContract.AnimeState.Loading) }
        viewModelScope.launch {
            try {
                val animeItem = withContext(Dispatchers.IO) { useCase.getTopAnime() }
                when (animeItem) {
                    is Result.Success -> setState {
                        copy(
                            animeState = HomeContract.AnimeState.Success(
                                animeList = animeItem.data
                            )
                        )
                    }

                    is Result.Error -> setState {
                        copy(
                            animeState = HomeContract.AnimeState.Error(animeItem.message)
                        )
                    }
                }
            } catch (e: Exception) {
                setState {
                    copy(
                        animeState = HomeContract.AnimeState.Error(
                            e.localizedMessage ?: "Unknown error"
                        )
                    )
                }
            }
        }
    }
}