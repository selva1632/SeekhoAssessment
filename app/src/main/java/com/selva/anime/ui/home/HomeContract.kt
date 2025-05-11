package com.selva.anime.ui.home

import com.selva.anime.domain.model.AnimeItem
import com.selva.anime.ui.base.UiEffect
import com.selva.anime.ui.base.UiEvent
import com.selva.anime.ui.base.UiState

class HomeContract {
    sealed class Event : UiEvent {
        data class OnAnimeSelected(val animeItem: AnimeItem) : Event()
        data object ErrorFetching: Event()
    }

    sealed class Effect : UiEffect {
        data object ShowToast : Effect()
    }

    data class State(
        val animeState: AnimeState = AnimeState.Idle
    ) : UiState

    sealed class AnimeState {
        data object Idle : AnimeState()
        data object Loading : AnimeState()
        data class Success(val animeList: List<AnimeItem>) : AnimeState()
        data class Error(val message: String) : AnimeState()
    }
}