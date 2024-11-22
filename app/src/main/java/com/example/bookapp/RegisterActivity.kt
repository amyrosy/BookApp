package com.example.bookapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var usernameInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var newPasswordInput: TextInputEditText
    private lateinit var registerButton: Button



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        usernameInput = findViewById(R.id.username_input_register)
        passwordInput = findViewById(R.id.password_input_register)
        newPasswordInput = findViewById(R.id.new_password_input_register)
        registerButton = findViewById(R.id.register_button)

        registerButton.setOnClickListener{
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            val newPassword = newPasswordInput.text.toString()

            var cancel = false
            var focusView: View? = null

            if (username.isEmpty()){
                usernameInput.error = "Username must not be empty"
                cancel = true
                focusView = usernameInput
            }
            if(!isValidEmail(username)){
                usernameInput.error = "Please enter a valid email address"
                cancel = true
                focusView = usernameInput
            }

            val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$" // Regex for password validation
            if(password.isEmpty()){
                passwordInput.error = "Password must not be empty"
                cancel = true
                focusView = passwordInput
            }
            else if(!password.matches(passwordPattern.toRegex())){
                passwordInput.error = "Password must be at least 8 characters, with at least one capital letter, one small letter, one digit, and one special character with no space in between."
                cancel = true
                focusView = passwordInput
            }

            if(newPassword.isEmpty()){
                newPasswordInput.error = "Confirm New Password"
                cancel = true
                focusView = newPasswordInput
            } else if(!newPassword.matches(passwordPattern.toRegex())){
                newPasswordInput.error = "Password must be at least 8 characters, with at least one capital letter, one small letter, one digit, and one special character with no space in between."
                cancel = true
                focusView = newPasswordInput
            }

            if(newPassword.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty()) {
                if (newPassword != password) {
                    Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show()
                } else {
                    if (cancel) {
                        focusView?.requestFocus()
                    } else {
                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

                        //to ForgetPassword activity

                    }
                }
            }
        }


    }
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        )
        return emailPattern.matcher(email).matches()
    }
}