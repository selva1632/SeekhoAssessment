package com.selva.anime.data.remote.model.character

import com.google.gson.annotations.SerializedName
import com.selva.anime.data.remote.model.anime.Images

data class Character(
    @SerializedName("images") val images: Images,
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)