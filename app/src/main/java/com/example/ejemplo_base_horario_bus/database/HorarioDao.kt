package com.example.ejemplo_base_horario_bus.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HorarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(horario: Horario)
    @Query("SELECT * FROM horario_bus ORDER BY hora_llegada ASC")
    fun obtenerTodos(): List<Horario>

    @Query("SELECT * FROM horario_bus WHERE nombre_parada = :nombreParada ORDER BY hora_llegada ASC")
    fun obtenerPorNombreParada(nombreParada: String): List<Horario>

}