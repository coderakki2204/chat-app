package com.lucky.chatapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogIn: Button
    private lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

//        FirebaseApp.initializeApp(this)
        mAuth = FirebaseAuth.getInstance()

        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        btnLogIn=findViewById(R.id.btn_login)
        btnSignUp=findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password);
        }
    }
    private fun login(email: String,password: String){

        //login for logging user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                   //code for login user
                    val intent=Intent(this@LogIn , MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@LogIn, "User does not exist", Toast.LENGTH_SHORT).show()

                }
            }
    }
}