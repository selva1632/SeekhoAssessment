package com.selva.anime.data.remote.service

import com.selva.anime.data.remote.model.anime.AnimeResponse
import com.selva.anime.data.remote.model.anime.InfoResponse
import com.selva.anime.data.remote.model.character.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeService {
    @GET("top/anime")
    suspend fun getTopAnime(): Response<AnimeResponse>

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): Response<InfoResponse>

    @GET("anime/{anime_id}/characters")
    suspend fun getCharacterData(
        @Path("anime_id") id: Int
    ): Response<CharacterResponse>

    companion object {
        const val BASE_URL = "https://api.jikan.moe/v4/"
    }
}