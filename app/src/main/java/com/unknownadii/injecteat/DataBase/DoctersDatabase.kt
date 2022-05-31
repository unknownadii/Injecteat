package com.quotes.spot.quotmotiv.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavedData::class,AppointmentData::class], version = 1)
abstract class DoctersDatabase : RoomDatabase() {
    abstract fun doctersDAO() : DoctersDAO
}