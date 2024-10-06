package com.example.cleanarchk.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cleanarchk.util.Converters

@Entity(tableName = "star_wars_character")
data class Character(
    @PrimaryKey
    val characterID: String,
    val characterName: String,
    val birthYear: String,
    val eye_color: String,
    @TypeConverters(Converters::class)
    val films: List<String?>,
    val gender: String,
    val hairColor: String,
    val height: String,
    val mass: String,
    val skin_color: String
)
