package com.example.cleanarchk.data.data_source.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchk.domain.model.Film

@Dao
interface FilmsDao {

    @Query("SELECT * FROM star_wars_film Where filmId = :id")
    fun getFilmFromId(id : String): Film?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film : Film)

}