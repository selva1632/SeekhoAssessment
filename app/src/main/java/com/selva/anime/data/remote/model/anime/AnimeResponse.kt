package com.selva.anime.data.remote.model.anime

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data") val data: List<AnimeData>
)