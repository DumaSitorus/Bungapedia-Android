package com.duma.bungapedia

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            FLAG_FULLSCREEN,
            FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_splash_screen)
        android.os.Handler().postDelayed(
            {
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            SPLASH_TIMER.toLong()
        )
    }
    companion object {
        private const val SPLASH_TIMER = 4000
    }
}