package com.example.savemyplace

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class tryActivity : AppCompatActivity() {

     lateinit var try_text_view: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_try)


        try_text_view = findViewById(R.id.try_text_view)


        val name = intent.getStringExtra("username")
        try_text_view.text = name


        println(name)
    }
}
