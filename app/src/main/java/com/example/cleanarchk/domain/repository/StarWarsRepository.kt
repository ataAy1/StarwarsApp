package com.example.cleanarchk.domain.repository

import com.example.cleanarchk.domain.model.Character
import com.example.cleanarchk.domain.model.Film
import com.example.cleanarchk.util.ResponseState
import kotlinx.coroutines.flow.Flow

interface StarWarsRepository {

    fun getAllCharacters(page: String): Flow<ResponseState<List<Character>>>

    suspend fun getFilmFromId(id: String): Film?

}