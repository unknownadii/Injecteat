package com.quotes.spot.quotmotiv.DataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DoctersDAO {

    // For Saved Docters
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedDocters (quotesFav : SavedData)

    @Delete
    suspend fun deleteSavedDoctors(quotesFav: SavedData)

    @Query("SELECT * FROM SavedDoctors")
    fun getAllSavedDoctors():LiveData<List<SavedData>>

    // Appointment
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointmentDocters (quotesWrite : AppointmentData)

    @Delete
    suspend fun deleteAppointmentDocters(quotesWrite: AppointmentData)

    @Query("SELECT * FROM AppointmentDocters")
    fun getAllAppointmentDocters():LiveData<List<AppointmentData>>
}