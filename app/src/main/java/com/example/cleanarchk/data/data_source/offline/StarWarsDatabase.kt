package com.example.cleanarchk.data.data_source.offline

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cleanarchk.domain.model.Film
import com.example.cleanarchk.domain.model.Character
import com.example.cleanarchk.util.Converters

@Database(entities = [Character::class, Film::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class StarWarsDatabase : RoomDatabase() {

    abstract fun charactersDao() : CharactersDao
    abstract fun filmsDao() : FilmsDao


    companion object {
        val MIGRATION_1_2 : Migration = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Perform necessary database migration operations here
            }

        }
    }
}