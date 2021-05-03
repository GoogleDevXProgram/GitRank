package com.example.gitrank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gitrank.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var navController: NavController
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initializeViews()
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }



    private fun initializeViews() {
        supportActionBar?.hide()
        val bottomNavigationView = mainBinding.bottomNav
        navController = findNavController(R.id.main_nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)
    }
}