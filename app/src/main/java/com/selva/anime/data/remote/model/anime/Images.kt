package com.selva.anime.data.remote.model.anime

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("jpg") val jpg: ImageFormats? = null,
    @SerializedName("webp") val webp: ImageFormats? = null
)