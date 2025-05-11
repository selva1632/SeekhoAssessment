package com.selva.anime.data.remote.model.character

import com.google.gson.annotations.SerializedName

data class CharacterData(
    @SerializedName("character") val character: Character,
    @SerializedName("role") val role: String,
)