package com.example.savemyplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var email_edit_text: EditText
    private lateinit var password_edit_text: EditText
    private lateinit var signin_button_view: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        email_edit_text = findViewById(R.id.email_edit_text)
        password_edit_text = findViewById(R.id.password_edit_text)
        signin_button_view = findViewById(R.id.signin_button_view)

        val signup_question = findViewById<TextView>(R.id.signup_question)
        signup_question.setOnClickListener{
            val intent = Intent(this, signup_Activity::class.java)
            startActivity(intent)
        }


        auth = FirebaseAuth.getInstance()

        signin_button_view.setOnClickListener {
            var email: String = email_edit_text.text.toString()
            var password: String = password_edit_text.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this@MainActivity, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

    }
}
