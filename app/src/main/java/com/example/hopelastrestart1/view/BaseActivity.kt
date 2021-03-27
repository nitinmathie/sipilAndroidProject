package com.example.hopelastrestart1.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import androidx.room.Update
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityAddOrganizationBinding
import com.example.hopelastrestart1.ui.home.organization.MyOrganizationsActivity
import com.example.hopelastrestart1.ui.home.profile.UpdateProfileActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import org.kodein.di.bindings.BaseMultiBinding

open class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        frameLayout = findViewById(R.id.container)
        toolbar = findViewById(R.id.toolbar_org)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.d_open, R.string.d_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        //  val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level dest  inations.
        /* appBarConfiguration = AppBarConfiguration(
             setOf(
                 R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
             ), drawerLayout
         )*/
        // setupActionBarWithNavController(navController, appBarConfiguration)
        //  navView.setupWithNavController(navController)
    }

    /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
         // Inflate the menu; this adds items to the action bar if it is present.
         menuInflater.inflate(R.menu.base, menu)
         return true
     }*/

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_update_profile -> {
                startActivity(Intent(applicationContext, UpdateProfileActivity::class.java))
            }
            R.id.nav_myorg -> {
                startActivity(Intent(applicationContext, MyOrganizationsActivity::class.java))
            }
        }
        return true
    }


    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            finish()
            //ExitApp()
        }
    }
    /* override fun onSupportNavigateUp(): Boolean {
         val navController = findNavController(R.id.nav_host_fragment)
         return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
     }*/
}