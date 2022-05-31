package com.quotes.spot.quotmotiv.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SavedDoctors")
data class SavedData(
    @PrimaryKey(autoGenerate = true)
    val idDocterSaved: Int,
    val docterNameSaved: String,
    val docterCategorySaved: String
)
