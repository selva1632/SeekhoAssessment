package com.selva.anime.data.remote.model.anime

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("youtube_id") val youtubeId: String?,
    @SerializedName("url") val url: String?
)