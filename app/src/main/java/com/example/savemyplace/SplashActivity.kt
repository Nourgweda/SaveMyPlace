package com.example.savemyplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class splash_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash)

        val logo_image=findViewById<ImageView>(R.id.logo_image_View)
        val app_name=findViewById<TextView>(R.id.application_name)
        val shake = AnimationUtils.loadAnimation(this,R.anim.slide_up);


        logo_image.startAnimation(shake)
        app_name.startAnimation(shake)



        //4second splash time
        Handler().postDelayed({
            //start main activity
            startActivity(Intent(this@splash_Activity, MainActivity::class.java))
            //finish this activity
            finish()
        },4000)


    }



}