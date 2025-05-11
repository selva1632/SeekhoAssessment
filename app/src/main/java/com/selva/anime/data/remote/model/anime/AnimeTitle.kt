package com.selva.anime.data.remote.model.anime

import com.google.gson.annotations.SerializedName

data class AnimeTitle(
    @SerializedName("type") val type: String,
    @SerializedName("title") val title: String
)