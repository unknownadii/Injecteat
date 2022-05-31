package com.unknownadii.injecteat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PrivacyAndAboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_and_about_us)
        val toolbar =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarPrivacy)
        //for showing toolbar
        setSupportActionBar(toolbar)
        // for getting back arrow on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}