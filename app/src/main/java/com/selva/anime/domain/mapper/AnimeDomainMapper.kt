package com.selva.anime.domain.mapper

import com.selva.anime.data.remote.model.anime.AnimeData
import com.selva.anime.domain.model.AnimeInfoItem
import com.selva.anime.domain.model.AnimeItem

fun AnimeData.toAnimeItem(): AnimeItem {
    return AnimeItem(
        id = malId,
        title = title ?: "",
        imageUrl = images?.jpg?.imageUrl,
        rating = rating ?: "",
        episode = episodes.toString()
    )
}

fun AnimeData.toAnimeInfo(): AnimeInfoItem {
    return AnimeInfoItem(
        id = malId,
        image = images?.jpg?.imageUrl,
        youtubeUrl = trailer?.url,
        youtubeId = trailer?.youtubeId,
        title = title?: "",
        plot = synopsis?: "",
        episode = episodes,
        rating = rating ?: "",
        genre = genres?.map { it.name } ?: emptyList()
    )
}