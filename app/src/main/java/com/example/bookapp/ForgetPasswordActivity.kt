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

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var usernameInput: TextInputEditText
    private lateinit var submitButton: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forget_password)

        usernameInput = findViewById(R.id.username_input)
        submitButton = findViewById(R.id.submit_button)


        submitButton.setOnClickListener{
            val username = usernameInput.text.toString()

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


            if(cancel){
                focusView?.requestFocus()
            }else{
                Toast.makeText(this, "OTP  send to your email", Toast.LENGTH_SHORT).show()

                //to another activity
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