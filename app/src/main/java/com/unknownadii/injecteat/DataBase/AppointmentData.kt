package com.quotes.spot.quotmotiv.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AppointmentDocters")
data class AppointmentData(
    @PrimaryKey(autoGenerate = true)
    val idDocterAppointment: Int,
    val docterNameAppointment: String,
    val docterCategoryAppointment: String,
    val docterDateAppointment: String,
    val docterTimeAppointment: String
)
