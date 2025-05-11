package com.selva.anime.data.remote.model.character

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data") val data: List<CharacterData>
)