package com.selva.anime.domain.mapper

import com.selva.anime.data.remote.model.character.Character
import com.selva.anime.domain.model.CharacterItem

fun Character.toCharacterItem(): CharacterItem {
    return CharacterItem(
        id = malId,
        name = name
    )
}