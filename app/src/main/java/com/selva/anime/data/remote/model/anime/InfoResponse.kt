package com.selva.anime.data.remote.model.anime

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("data")
    val data: AnimeData
)