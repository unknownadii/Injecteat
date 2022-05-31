package com.unknownadii.injecteat

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.unknownadii.injecteat.Fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbarHome =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.topAppBar)

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val messagesFragment = MessagesFragment()
        val scheduleFragment = ScheduleFragment()
        val savedFragment = SavedFragment()

        setCurrentFragment(homeFragment)

        //for showing toolbar
        setSupportActionBar(toolbarHome)
        val drawerLayout =
            findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout, R.string.open, R.string.close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val drawer_navigation_view =
            findViewById<com.google.android.material.navigation.NavigationView>(R.id.drawer_navigation_view)
        drawer_navigation_view.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.menuHome -> {
                    Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show()

                }
                R.id.menuProfile -> {
                    Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, PatientProfileActivity::class.java))
                }
                R.id.menuPrivacy -> {
                    Toast.makeText(this, "Privacy Clicked", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, PrivacyAndAboutUsActivity::class.java))

                }
                R.id.menuSetting -> {
                    Toast.makeText(this, "Setting Clicked", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, PatientProfileSetting::class.java))

                }
                R.id.menuAboutUs -> {
                    Toast.makeText(this, "About us Clicked", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, PrivacyAndAboutUsActivity::class.java))
                }
                R.id.menuLogout -> {
                    startActivity(Intent(this, SplashScreen::class.java))
                    Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            true
        }

        //for the bottom navigation bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.background = null
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottomHome -> {
                    setAppBar()
                    setCurrentFragment(homeFragment)
                }
                R.id.bottomSearch -> {
                    hideAppBar()
                    setCurrentFragment(searchFragment)

                }
                R.id.bottomMessage -> {
                    hideAppBar()
                    setCurrentFragment(messagesFragment)

                }
                R.id.bottomCalender -> {
                    hideAppBar()
                    setCurrentFragment(scheduleFragment)

                }
                R.id.bottomSave -> {
                    hideAppBar()
                    setCurrentFragment(savedFragment)

                }
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.rv_Fragment, fragment)
            commit()
        }
    }


    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun setAppBar() {
        supportActionBar?.show()
    }

    //for upper menubar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_header_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}