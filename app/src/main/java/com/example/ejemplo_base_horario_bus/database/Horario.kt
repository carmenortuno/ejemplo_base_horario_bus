package com.example.ejemplo_base_horario_bus.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "horario_bus")
data class Horario(
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "nombre_parada") val nombreParada: String,
    @NonNull @ColumnInfo(name = "hora_llegada") val horaLlegada: Int
)
