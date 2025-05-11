package com.selva.anime.domain.usecase.impl

import com.selva.anime.baseutils.Result
import com.selva.anime.domain.model.AnimeInfoItem
import com.selva.anime.domain.model.AnimeItem
import com.selva.anime.domain.model.CharacterItem
import com.selva.anime.domain.usecase.interfaces.GetAnimeUseCase
import com.selva.anime.repository.interfaces.AnimeRepository
import javax.inject.Inject

class GetAnimeUseCaseImpl @Inject constructor(
    private val repository: AnimeRepository
): GetAnimeUseCase {
    override suspend fun getTopAnime(): Result<List<AnimeItem>> {
        return repository.getTopAnime()
    }

    override suspend fun getAnimeId(id: Int): Result<AnimeInfoItem> {
        return repository.getAnimeById(id)
    }

    override suspend fun getCharacterData(id: Int): Result<List<CharacterItem>> {
        return repository.getCharacterData(id)
    }
}