package com.example.prueba3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView

// Actividad para el registro de nuevos usuarios en la aplicación
class RegisterActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        firebaseAuth = FirebaseAuth.getInstance()

        // Referencias a los campos de entrada y botones de la interfaz
        val emailInput = findViewById<TextInputEditText>(R.id.etRegisterEmail)
        val passwordInput = findViewById<TextInputEditText>(R.id.etRegisterPassword)
        val createAccountButton = findViewById<MaterialButton>(R.id.btnCreateAccount)
        val backToLogin = findViewById<TextView>(R.id.tvBackToLogin)
        // Acción del botón "Crear cuenta"
        createAccountButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                registerUser(email, password)
            } else {

                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }


        backToLogin.setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    // Método para registrar un usuario en Firebase
    private fun registerUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    // Error durante el registro: mostrar mensaje con el error
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}

