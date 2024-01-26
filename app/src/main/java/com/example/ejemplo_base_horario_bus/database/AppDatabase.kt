package com.example.ejemplo_base_horario_bus.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Horario::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun horarioDao(): HorarioDao

    //Para inicializar valores en la BD
    private class HorarioDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var HorarioDao = database.horarioDao()

                    var horario = Horario(1,"Estudiar",3)
                    HorarioDao.insertar(horario)


                }
            }
        }
    }
    companion object{
        //Singleton para evitar múltiples instancias de apertura de la base de datos
        @Volatile
        private var INSTANCE : AppDatabase? =null

        fun getDatabase(context: Context) : AppDatabase{
            // Si la instancia no es null, se devuelve, en caso contrario se crea
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bus_database"
                )
                    //.addCallback(HorarioDatabaseCallback(scope))
                    .build()
                INSTANCE=instance

                instance
            }
        }
    /*companion object{
        //Singleton para evitar múltiples instancias de apertura de la base de datos
        @Volatile
        private var INSTANCE : AppDatabase? =null

        fun getDatabase(context: Context, scope: CoroutineScope) : AppDatabase{
            // Si la instancia no es null, se devuelve, en caso contrario se crea
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bus_database"
                )
                    .addCallback(HorarioDatabaseCallback(scope))
                    .build()
                INSTANCE=instance

                instance
            }
        }*/
    }
}