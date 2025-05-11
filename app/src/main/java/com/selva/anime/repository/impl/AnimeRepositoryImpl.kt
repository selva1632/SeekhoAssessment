package com.selva.anime.repository.impl

import com.selva.anime.baseutils.Result
import com.selva.anime.data.remote.service.AnimeService
import com.selva.anime.domain.mapper.toAnimeInfo
import com.selva.anime.domain.mapper.toAnimeItem
import com.selva.anime.domain.mapper.toCharacterItem
import com.selva.anime.domain.model.AnimeInfoItem
import com.selva.anime.domain.model.AnimeItem
import com.selva.anime.domain.model.CharacterItem
import com.selva.anime.repository.interfaces.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val apiService: AnimeService
) : AnimeRepository {
    override suspend fun getTopAnime(): Result<List<AnimeItem>> {
        val response = apiService.getTopAnime()

        return if (response.isSuccessful) {
            response.body()?.let { data ->
                Result.Success(data.data.map { it.toAnimeItem() })
            } ?: Result.Error("Empty Body")
        } else {
            Result.Error(response.message())
        }
    }

    override suspend fun getAnimeById(id: Int): Result<AnimeInfoItem> {
        val response = apiService.getAnimeById(id)

        return if (response.isSuccessful) {
            response.body()?.let { data ->
                val newItem = data.data.toAnimeInfo()
                Result.Success(newItem)
            } ?: Result.Error("empty body")
        } else {
            Result.Error(response.message())
        }
    }

    override suspend fun getCharacterData(id: Int): Result<List<CharacterItem>> {
        val response = apiService.getCharacterData(id)

        return if (response.isSuccessful) {
            response.body()?.let { data ->
                val mainCharacter = data.data.filter {
                    it.role == "Main"
                }.map {
                    it.character.toCharacterItem()
                }
                Result.Success(mainCharacter)
            } ?: Result.Error("empty body")
        } else {
            Result.Error(response.message())
        }
    }
}