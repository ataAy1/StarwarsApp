package com.example.cleanarchk.data.data_source.remote.dto.CharacterDTO

data class CharactersDTO(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<Result?>?
)