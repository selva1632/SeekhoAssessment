package com.selva.anime.data.remote.model.anime

import com.google.gson.annotations.SerializedName

data class ImageFormats(
    @SerializedName("image_url") val imageUrl: String
)