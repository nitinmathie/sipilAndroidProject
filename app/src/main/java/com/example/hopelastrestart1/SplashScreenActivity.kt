package com.example.hopelastrestart1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hopelastrestart1.ui.admin.AdminDashBoardActivity
import com.example.hopelastrestart1.ui.auth.LoginActivity
import com.example.hopelastrestart1.ui.home.organization.MyOrganizationsActivity
import com.example.hopelastrestart1.ui.home.plen.PlanActivity
import com.example.hopelastrestart1.ui.siteEngineer.SiteEngActivity
import com.example.hopelastrestart1.ui.store.StoreHomeActivity
import com.example.hopelastrestart1.ui.store.StoreManagerMaterialsActivity

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
                        val loginRole = pref.getString("role", null)
                        val stroeName = pref.getString("storeName", null)
                        GlobalData.getInstance.token = token
                        GlobalData.getInstance.userEmail = email
                        GlobalData.getInstance.loginRole = loginRole
                        if (pref.getString("role", null).equals("Admin")) {
                            GlobalData.getInstance.loginRole = "0"
                            val intent =
                                Intent(applicationContext, AdminDashBoardActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else if (pref.getString("role", null).equals("Plan")) {
                            GlobalData.getInstance.loginRole = "1"
                            val intent = Intent(
                                applicationContext,
                                PlanActivity::class.java
                            )
                            startActivity(intent)
                            finish()
                        } else if (pref.getString("role", null).equals("Site")) {
                            GlobalData.getInstance.loginRole = "2"
                            val intent = Intent(
                                applicationContext,
                                SiteEngActivity::class.java
                            )
                            startActivity(intent)
                            finish()
                        } else if (pref.getString("role", null).equals("StoreKeeper")) {
                            GlobalData.getInstance.loginRole = "3"
                            GlobalData.getInstance.storeName = stroeName
                            val intent = Intent(
                                applicationContext,
                                StoreHomeActivity::class.java
                            )
                            startActivity(intent)
                            finish()
                        } else if (pref.getString("role", null).equals("StoreManager")) {
                            GlobalData.getInstance.loginRole = "4"
                            GlobalData.getInstance.storeName = stroeName
                            val intent = Intent(
                                applicationContext,
                                StoreManagerMaterialsActivity::class.java
                            )
                            startActivity(intent)
                            finish()
                        } else {
                            val intent =
                                Intent(applicationContext, MyOrganizationsActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
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