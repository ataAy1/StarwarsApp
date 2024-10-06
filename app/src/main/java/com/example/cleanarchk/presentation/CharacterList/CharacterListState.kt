package com.example.cleanarchk.presentation.CharacterList
import com.example.cleanarchk.domain.model.Character

data class CharacterListState(
    val isLoading : Boolean = true,
    val characters : List<Character> = emptyList(),
    val error : String = ""
)