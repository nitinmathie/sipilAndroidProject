package com.example.hopelastrestart1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hopelastrestart1.ui.auth.LoginActivity
import com.example.hopelastrestart1.ui.home.organization.OrganizationActivity
import com.example.hopelastrestart1.ui.siteEngineer.SiteEngActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep((1 * 1000).toLong())
                    // After 5 seconds redirect to another intent
                    val pref = getSharedPreferences("prefs", MODE_PRIVATE)
                    val isLogedIn = pref.getBoolean("isLogedIn", false)
                    if (isLogedIn) {
                        val email = pref.getString("email", null)
                        val token = pref.getString("token", null)
                        GlobalData.getInstance.token = token
                        GlobalData.getInstance.userEmail = email
                        val intent = Intent(applicationContext, OrganizationActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()


    }
}