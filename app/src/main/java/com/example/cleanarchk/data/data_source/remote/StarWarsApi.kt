package com.example.cleanarchk.data.data_source.remote

import com.example.cleanarchk.data.data_source.remote.dto.CharacterDTO.CharactersDTO
import com.example.cleanarchk.data.data_source.remote.dto.FilmDTO.FilmDTO
import com.example.cleanarchk.domain.model.Character
import com.example.cleanarchk.domain.model.Film
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {

    @GET("api/people/")
    suspend fun getAllCharacters(@Query("page")page : String) : CharactersDTO

    @GET("api/films/{id}")
    suspend fun getFilmFromId(@Path("id:") filmId: String): FilmDTO

}