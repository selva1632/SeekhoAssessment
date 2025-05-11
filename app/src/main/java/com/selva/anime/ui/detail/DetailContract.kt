package com.selva.anime.ui.detail

import com.selva.anime.domain.model.AnimeInfoItem
import com.selva.anime.domain.model.CharacterItem
import com.selva.anime.ui.base.UiEffect
import com.selva.anime.ui.base.UiEvent
import com.selva.anime.ui.base.UiState

class DetailContract {
    sealed class DetailEvent: UiEvent
    sealed class DetailEffect: UiEffect

    data class State(
        val detailState: DetailState
    ): UiState

    sealed class DetailState {
        data class Error(val message: String): DetailState()
        data object Idle: DetailState()
        data object Loading: DetailState()
        data class Success(val info: AnimeInfos): DetailState()
    }
}

data class AnimeInfos(
    val animeInfo: AnimeInfoItem,
    val character: List<CharacterItem>
)