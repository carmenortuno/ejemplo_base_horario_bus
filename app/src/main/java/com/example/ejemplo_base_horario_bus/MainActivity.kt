package com.example.ejemplo_base_horario_bus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ejemplo_base_horario_bus.database.AppDatabase
import com.example.ejemplo_base_horario_bus.database.Horario
import com.example.ejemplo_base_horario_bus.database.HorarioDao
import com.example.ejemplo_base_horario_bus.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var h : Horario
    private lateinit var hdao : HorarioDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //las operaciones de base de datos deben realizarse en un
        // hilo diferente al hilo principal para evitar bloqueos
        GlobalScope.launch{
            val db = AppDatabase.getDatabase(applicationContext)

            var HorarioDao = db.horarioDao()

            var horario = Horario(3,"El Campico",5)
            HorarioDao.insertar(horario)


        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //setupActionBarWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}