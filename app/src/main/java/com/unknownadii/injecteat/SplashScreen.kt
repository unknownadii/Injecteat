package com.unknownadii.injecteat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        findViewById<Button>(R.id.registerButton).apply {
            this.setOnClickListener {
                val intent = Intent(this@SplashScreen, LoginAndRegisterActivity::class.java)
                intent.putExtra("Register","Register");
                startActivity(intent);
            }
        }
        findViewById<Button>(R.id.loginButton).apply {
            this.setOnClickListener {
                val intent = Intent(this@SplashScreen, LoginAndRegisterActivity::class.java)
                intent.putExtra("Login","Login");
                startActivity(intent);
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val todo_pref = getSharedPreferences("injecteat", MODE_PRIVATE)
        if (todo_pref.contains("token")) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}