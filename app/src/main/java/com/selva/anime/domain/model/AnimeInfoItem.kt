package com.selva.anime.domain.model

data class AnimeInfoItem(
    val id: Int,
    val image: String?,
    val youtubeUrl: String?,
    val youtubeId: String?,
    val title: String,
    val plot: String,
    val episode: Int,
    val rating: String,
    val genre: List<String>?
)
