package com.selva.anime.data.remote.model.anime

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name") val name: String
)