package com.example.sharelove.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.sharelove.R
import com.example.sharelove.fragment.*
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    var previouMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)
        setUpToolBar()
        openDashboard()
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.opendrawer,
            R.string.closedrawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener {

            if (previouMenuItem != null) {
                previouMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previouMenuItem = it

            when (it.itemId) {
                R.id.dashboard -> {
                    openDashboard()
                    drawerLayout.closeDrawers()
                    Toast.makeText(this@MainActivity, "Dashboard is clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.myProfile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            ProfileFragment()
                        )
                        .addToBackStack("My Profile")
                        .commit()

                    supportActionBar?.title = "My Profile"
                    drawerLayout.closeDrawers()
                    Toast.makeText(this@MainActivity, "my Profile is clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.aboutus -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            AboutUsFragment()
                        )
                        .addToBackStack("About Us")
                        .commit()

                    supportActionBar?.title = "About Us"
                    drawerLayout.closeDrawers()
                    Toast.makeText(this@MainActivity, "About Us is clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.login -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            LoginFragment()
                        )
                        .addToBackStack("Login")
                        .commit()
                    supportActionBar?.title = "Login"
                    drawerLayout.closeDrawers()
                    Toast.makeText(this@MainActivity, "Login is clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.SignUp -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            SignUpFragment()
                        )
                        .addToBackStack("Sign Up")
                        .commit()
                    supportActionBar?.title = "Sign Up"
                    drawerLayout.closeDrawers()
                    Toast.makeText(this@MainActivity, "Sign Up is clicked", Toast.LENGTH_SHORT)
                        .show()
                }

            }
            return@setNavigationItemSelectedListener true
        }


    }

    fun openDashboard() {
        val fragment = DashboardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
        supportActionBar?.title = "Share Love"
        navigationView.setCheckedItem(R.id.dashboard)
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

        when (supportFragmentManager.findFragmentById(R.id.frameLayout)) {
            !is DashboardFragment -> openDashboard()
            else -> super.onBackPressed()
        }
    }

    fun setUpToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Share Love"
    }


}