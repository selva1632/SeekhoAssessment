package com.selva.anime.ui.detail

import android.content.Intent
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.selva.anime.baseutils.Result
import com.selva.anime.domain.usecase.interfaces.GetAnimeUseCase
import com.selva.anime.ui.base.BaseViewModel
import com.selva.anime.ui.home.HomeActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetAnimeUseCase
) : BaseViewModel<DetailContract.DetailEffect, DetailContract.DetailEvent, DetailContract.State>() {
    override fun createInitialState(): DetailContract.State {
        return DetailContract.State(
            DetailContract.DetailState.Idle
        )
    }

    override fun handleEvent(event: DetailContract.DetailEvent) {}

    fun fetchRemoteData(intent: Intent?) {
        setState { copy(detailState = DetailContract.DetailState.Loading) }

        val id = intent?.getIntExtra(HomeActivity.ANIME_ITEM, -1) ?: -1
        Log.i(TAG, "anime-id: $id")

        if (id == -1) {
            setState { copy(detailState = DetailContract.DetailState.Error("anime id is invalid")) }
        }

        viewModelScope.launch {
            try {
                val animeInfoResult = withContext(Dispatchers.IO) { useCase.getAnimeId(id = id) }
                val animeInfo = when (animeInfoResult) {
                    is Result.Error -> {
                        setState {
                            copy(
                                detailState = DetailContract.DetailState.Error(
                                    animeInfoResult.message
                                )
                            )
                        }
                        return@launch
                    }

                    is Result.Success -> {
                        animeInfoResult.data
                    }
                }

                val characterItemResult =
                    withContext(Dispatchers.IO) { useCase.getCharacterData(id = id) }
                val characterItem = when (characterItemResult) {
                    is Result.Error -> {
                        setState {
                            copy(
                                detailState = DetailContract.DetailState.Error(
                                    characterItemResult.message
                                )
                            )
                        }
                        emptyList()
                    }

                    is Result.Success -> {
                        characterItemResult.data
                    }
                }

                val mergedList = AnimeInfos(
                    animeInfo = animeInfo,
                    character = characterItem
                )

                setState {
                    copy(
                        detailState = DetailContract.DetailState.Success(
                            mergedList
                        )
                    )
                }

            } catch (e: Exception) {
                setState {
                    copy(
                        detailState = DetailContract.DetailState.Error(
                            e.localizedMessage ?: "Unknown error"
                        )
                    )
                }
            }
        }
    }

    companion object {
        const val TAG = "DetailViewModel"
    }
}