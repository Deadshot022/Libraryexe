package com.android.libraryexe

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.libraryexe.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passEt.text.toString()
            registerUser(email, password)
        }

        binding.rdrBtn.setOnClickListener {
            // Navigate to the login activity
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign up success, update UI with the signed-in user's information
                Toast.makeText(baseContext, "Sign up successful.", Toast.LENGTH_SHORT).show()
                // Navigate to the home activity
            } else {
                // Sign up failed, display a message to the user.
                Toast.makeText(baseContext, "Sign up failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}