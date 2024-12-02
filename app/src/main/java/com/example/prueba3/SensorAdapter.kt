package com.example.prueba3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador para manejar y mostrar la lista de sensores
class SensorAdapter(private val sensorList: List<Sensor>) :
    RecyclerView.Adapter<SensorAdapter.SensorViewHolder>() {

    // Crear nuevas vistas
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sensor, parent, false)
        return SensorViewHolder(view)
    }

    // Reemplazar el contenido de una vista (invocado por el LayoutManager)
    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val sensor = sensorList[position]
        holder.bind(sensor)
    }
    // Retornar el tamaño del dataset (invocado por el LayoutManager)
    override fun getItemCount(): Int = sensorList.size


    class SensorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referencias a los elementos del diseño de ítem
        private val modelTextView: TextView = itemView.findViewById(R.id.tvModel)
        private val codeTextView: TextView = itemView.findViewById(R.id.tvCode)
        private val locationTextView: TextView = itemView.findViewById(R.id.tvLocation)

        // Asignar los valores del sensor a los elementos de la vista
        fun bind(sensor: Sensor) {
            modelTextView.text = "Modelo: ${sensor.model}"
            codeTextView.text = "Código: ${sensor.code}"
            locationTextView.text = "Ubicación: ${sensor.location}"
        }
    }
}

// Clase de datos que representa un Sensor con sus propiedades
data class Sensor(
    val model: String = "",
    val code: String = "",
    val location: String = ""
)

