package com.example.ejemplo_base_horario_bus

import android.app.Application
import com.example.ejemplo_base_horario_bus.database.AppDatabase

class HorarioBusApplication:Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this)}
}