package com.example.prueba3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.*

class SensorListActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var sensorList: ArrayList<Sensor>
    private lateinit var adapter: SensorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor_list)

        // Configuración del RecyclerView para mostrar la lista de sensores
        val recyclerView = findViewById<RecyclerView>(R.id.rvSensors)
        recyclerView.layoutManager = LinearLayoutManager(this)
        sensorList = ArrayList() //
        adapter = SensorAdapter(sensorList)
        recyclerView.adapter = adapter

        // Configuración del botón para volver al menú principal
        val backToMenuButton = findViewById<MaterialButton>(R.id.btnBackToMenu)
        backToMenuButton.setOnClickListener {
            val intent = Intent(this, postlogin::class.java)
            startActivity(intent)
            finish()
        }

        // Inicialización de la referencia a la base de datos Firebase en el nodo "sensors"
        database = FirebaseDatabase.getInstance().reference.child("sensors")

        // Configurar el listener para obtener datos en tiempo real desde Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                sensorList.clear()

                for (data in snapshot.children) {
                    val sensor = data.getValue(Sensor::class.java)
                    sensor?.let { sensorList.add(it) }
                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejo de errores al acceder a Firebase (actualmente vacío)
            }
        })
    }
}
