package com.selva.anime.data.remote.model.anime

import com.google.gson.annotations.SerializedName

data class AnimeData(
    @SerializedName("mal_id") val malId: Int = 0,
    @SerializedName("title") val title: String? = null,
    @SerializedName("title_english") val titleEnglish: String? = null,
    @SerializedName("synopsis") val synopsis: String? = null,
    @SerializedName("episodes") val episodes: Int = 0,
    @SerializedName("rating") val rating: String? = null,
    @SerializedName("duration") val duration: String? = null,
    @SerializedName("score") val score: Double = 0.0,
    @SerializedName("scored_by") val scoredBy: Int = 0,
    @SerializedName("year") val year: Int = 0,
    @SerializedName("rank") val rank: Int = 0,
    @SerializedName("type") val type: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("season") val season: String? = null,
    @SerializedName("genres") val genres: List<Genre>? = null,
    @SerializedName("trailer") val trailer: Trailer? = null,
    @SerializedName("titles") val titles: List<AnimeTitle>? = null,
    @SerializedName("images") val images: Images? = null
)