package com.example.bookapp

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

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var forgetPasswordText: TextView
    private lateinit var signUpText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        forgetPasswordText = findViewById(R.id.forget_password)
        signUpText = findViewById(R.id.sign_up)

        loginButton.setOnClickListener{
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

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
                passwordInput.error = "Password must be at least 8 characters, with at least one capital letter, one small letter, one digit, and one special character"
                cancel = true
                focusView = passwordInput
            }

            if(cancel){
                focusView?.requestFocus()
            }else{
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                //to another activity
            }
        }

        forgetPasswordText.setOnClickListener{
            //to another activity
            val intent = Intent(this,ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        signUpText.setOnClickListener{
            //to register activity
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

    }
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        )
        return emailPattern.matcher(email).matches()
    }
}