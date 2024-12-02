package com.example.prueba3

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

        val emailInput = findViewById<TextInputEditText>(R.id.etEmail)
        val passwordInput = findViewById<TextInputEditText>(R.id.etPassword)
        val loginButton = findViewById<MaterialButton>(R.id.btnLogin)
        val registerTextView = findViewById<TextView>(R.id.tvRegistrar)

        registerTextView.setOnClickListener {

            startActivity(Intent(this, RegisterActivity::class.java))
        }


        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    startActivity(Intent(this, postlogin::class.java))
                    finish()
                } else {

                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}
