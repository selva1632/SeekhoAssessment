package com.selva.anime.ui.home

import com.selva.anime.domain.model.AnimeItem

sealed class HomeNavigationEvent {
    data class NavigateToAnimeDetails(val animeItem: AnimeItem) : HomeNavigationEvent()
}