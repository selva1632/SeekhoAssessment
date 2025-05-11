package com.selva.anime.repository.interfaces

import com.selva.anime.baseutils.Result
import com.selva.anime.domain.model.AnimeInfoItem
import com.selva.anime.domain.model.AnimeItem
import com.selva.anime.domain.model.CharacterItem

interface AnimeRepository {
    suspend fun getTopAnime(): Result<List<AnimeItem>>
    suspend fun getAnimeById(id: Int): Result<AnimeInfoItem>
    suspend fun getCharacterData(id: Int): Result<List<CharacterItem>>
}