package com.example.savemyplace

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.text.TextUtils
import android.util.Patterns
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.registration_alert_dialog.*
import kotlinx.android.synthetic.main.registration_alert_dialog.view.*

class signup_Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email_edit_text: EditText
    private lateinit var password_edit_text: EditText
    private lateinit var signup_button_view: Button
    private lateinit var ok_button_view: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        email_edit_text = findViewById(R.id.email_edit_text_signup)
        password_edit_text = findViewById(R.id.password_edit_text_signup)
        signup_button_view = findViewById(R.id.signup_button_view)



        signup_button_view.setOnClickListener{
            var email: String = email_edit_text.text.toString()
            var password: String = password_edit_text.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email_edit_text.text.toString()).matches()) {
                Toast.makeText(this, "Enter a valid E-mail", Toast.LENGTH_LONG).show()

            }

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener{ task ->
                    if(task.isSuccessful){

                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.registration_alert_dialog, null)
                        //AlertDialogBuilder
                        val mBuilder = AlertDialog.Builder(this)
                            .setView(mDialogView)
                        //show dialog
                        val  mAlertDialog = mBuilder.show()
                        mDialogView.ok_button_view.setOnClickListener {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()

                        }

                    }else {
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}


