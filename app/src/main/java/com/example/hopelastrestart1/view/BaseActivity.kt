package com.example.hopelastrestart1.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.ui.AppBarConfiguration
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.model.GetUserProfile
import com.example.hopelastrestart1.ui.admin.AdminDashBoardActivity
import com.example.hopelastrestart1.ui.auth.LoginActivity
import com.example.hopelastrestart1.ui.home.organization.MyOrganizationsActivity
import com.example.hopelastrestart1.ui.home.plen.PlanActivity
import com.example.hopelastrestart1.ui.home.profile.UpdateProfileActivity
import com.example.hopelastrestart1.ui.home.project.MyProjectsActivity
import com.example.hopelastrestart1.ui.home.project.ProjectActivity
import com.example.hopelastrestart1.ui.home.project.StoreActivity
import com.example.hopelastrestart1.ui.siteEngineer.SiteEngActivity
import com.example.hopelastrestart1.ui.store.*
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_organization.*


open class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    private lateinit var viewModel: UserViewModel
    lateinit var inflater: MenuInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        frameLayout = findViewById(R.id.container)
        toolbar = findViewById(R.id.toolbar_org)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(UserViewModel::class.java)

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

        val getUserProfile = GetUserProfile(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!, ""
        )
        getCurrentUserProfile(getUserProfile)

        /*if (GlobalData.getInstance.loginRole.equals("0")) {
            showItem()
        } else {
            hideItem()
        }*/
        if (GlobalData.getInstance.loginRole.equals("3") || GlobalData.getInstance.loginRole.equals(
                "4"
            )
        ) {
            hideItem()
            showItem()
        }

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
    private fun hideItem() {
        val nav_Menu: Menu = navigationView.menu
        nav_Menu.findItem(R.id.nav_adduser).setVisible(false)
    }

    private fun showItem() {
        val nav_Menu: Menu = navigationView.menu
       // nav_Menu.findItem(R.id.nav_machies_store).setVisible(true)
        nav_Menu.findItem(R.id.nav_add_machine_invoice).setVisible(true)
        nav_Menu.findItem(R.id.nav_add_material_invoice).setVisible(true)
    }

    /*  private fun showItem() {
          val nav_Menu: Menu = navigationView.menu
          nav_Menu.findItem(R.id.nav_adduser).setVisible(true)
          nav_Menu.findItem(R.id.nav_addstore).setVisible(true)
      }

      private fun hideItem() {
          val nav_Menu: Menu = navigationView.menu
          nav_Menu.findItem(R.id.nav_adduser).setVisible(false)
          nav_Menu.findItem(R.id.nav_addstore).setVisible(false)
      }*/
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_update_profile -> {
                startActivity(Intent(applicationContext, UpdateProfileActivity::class.java))
            }
            R.id.nav_myorg -> {
                startActivity(Intent(applicationContext, MyOrganizationsActivity::class.java))
            }
            R.id.nav_project -> {
                startActivity(Intent(applicationContext, MyProjectsActivity::class.java))
            }
            R.id.nav_adduser -> {
                startActivity(Intent(applicationContext, ProjectActivity::class.java))
            }
            R.id.nav_addstore -> {
                startActivity(Intent(applicationContext, StoreActivity::class.java))
            }
         /*   R.id.nav_machies_store -> {
                startActivity(Intent(applicationContext, StoreMachinesActivity::class.java))
            }*/
            R.id.nav_add_machine_invoice -> {
                startActivity(Intent(applicationContext, MachineInvoicesListActivity::class.java))
            }
            R.id.nav_add_material_invoice -> {
                startActivity(Intent(applicationContext, MaterialListInvoicesActivity::class.java))
            }
            R.id.nav_logout -> {

                val myPrefs = getSharedPreferences(
                    "prefs",
                    Context.MODE_PRIVATE
                )
                val editor = myPrefs.edit()
                editor.clear()
                editor.apply()
                GlobalData.getInstance.reset()
                val intent = Intent(
                    applicationContext,
                    LoginActivity::class.java
                )
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        inflater = menuInflater
        inflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (GlobalData.getInstance.loginRole.equals("0")) {
            val intent = Intent(
                applicationContext,
                AdminDashBoardActivity::class.java
            )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        } else if (GlobalData.getInstance.loginRole.equals("1")) {
            val intent = Intent(
                applicationContext,
                PlanActivity::class.java
            )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        } else if (GlobalData.getInstance.loginRole.equals("2")) {
            val intent = Intent(
                applicationContext,
                SiteEngActivity::class.java
            )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        } else if (GlobalData.getInstance.loginRole.equals("3")) {
            val intent = Intent(
                applicationContext,
                StoreHomeActivity::class.java
            )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()

        } else {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getCurrentUserProfile(getUserProfile: GetUserProfile) {
        viewModel.getCurrentUserProfile(getUserProfile).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { usersProfile -> usersProfile.body() }
                        val userProfile = resource.data?.body()
                        val header: View = navigationView.getHeaderView(0)
                        val headerTitle = header.findViewById<TextView>(R.id.tv_nav_header_title)
                        val navSubHeaderSubTitle =
                            header.findViewById<TextView>(R.id.tv_nav_header_subtitle)
                        headerTitle.setText(userProfile?.first_name)
                        navSubHeaderSubTitle.setText(userProfile?.user_email)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }
}