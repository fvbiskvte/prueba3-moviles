package com.example.prueba3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// Actividad para agregar un nuevo sensor a la base de datos
class AddSensorActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_sensor)

        // Inicializar Firebase Database
        database = FirebaseDatabase.getInstance().reference

        // Referencias a los campos de entrada y botones en el diseño
        val modelInput = findViewById<TextInputEditText>(R.id.etModel)
        val codeInput = findViewById<TextInputEditText>(R.id.etCode)
        val locationInput = findViewById<TextInputEditText>(R.id.etLocation)
        val addSensorButton = findViewById<MaterialButton>(R.id.btnAddSensor)
        val backToMenuButton = findViewById<MaterialButton>(R.id.btnBackToMenu)

        // Configurar acción para agregar un sensor
        addSensorButton.setOnClickListener {
            // Leer los datos ingresados por el usuario
            val model = modelInput.text.toString().trim()
            val code = codeInput.text.toString().trim()
            val location = locationInput.text.toString().trim()

            // Verificar si todos los campos están completos
            if (model.isNotEmpty() && code.isNotEmpty() && location.isNotEmpty()) {
                addSensorToFirebase(model, code, location)
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar acción para volver al menú principal
        backToMenuButton.setOnClickListener {
            val intent = Intent(this, postlogin::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Método para agregar un sensor a Firebase
    private fun addSensorToFirebase(model: String, code: String, location: String) {
        val sensorId = database.push().key // Generar un ID único para el sensor
        if (sensorId == null) {
            Toast.makeText(this, "Error al generar el ID del sensor", Toast.LENGTH_SHORT).show()
            return
        }

        // Crear una instancia de la clase Sensor con los datos ingresados
        val sensor = Sensor(model, code, location)

        // Guardar el sensor en la base de datos bajo el nodo "sensors"
        database.child("sensors").child(sensorId).setValue(sensor)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Sensor agregado correctamente", Toast.LENGTH_SHORT).show()

                    // Volver al menú principal después de agregar el sensor
                    val intent = Intent(this, postlogin::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Mostrar un mensaje de error si ocurre un problema
                    Toast.makeText(this, "Error al agregar el sensor: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Clase de datos para representar un sensor
    data class Sensor(val model: String, val code: String, val location: String)
}




