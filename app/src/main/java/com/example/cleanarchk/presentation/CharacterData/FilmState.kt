package com.example.cleanarchk.presentation.CharacterData

import com.example.cleanarchk.domain.model.Film

data class FilmState(
    var isLoading : Boolean = false,
    var film : List<Film> = emptyList(),
    var error : String = ""
)