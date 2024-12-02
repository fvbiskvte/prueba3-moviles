package com.example.prueba3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

// Clase para la actividad post-login, que sirve como menú principal de la aplicación
class postlogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_postlogin)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración del botón "Cerrar sesión"
        findViewById<MaterialButton>(R.id.btnLogout).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Configuración del botón "Agregar Sensor"
        findViewById<MaterialButton>(R.id.btnAddSensor).setOnClickListener {
            val intent = Intent(this, AddSensorActivity::class.java)
            startActivity(intent)
        }

        // Configuración del botón "Historial"
        findViewById<MaterialButton>(R.id.btnHistory).setOnClickListener {
            // Navegar a la actividad HistoryActivity
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        // Configuración del botón "Lista de Sensores"
        val listButton = findViewById<Button>(R.id.btnListSensors).setOnClickListener {
            val intent = Intent(this, SensorListActivity::class.java)
            startActivity(intent)
        }
    }
}
