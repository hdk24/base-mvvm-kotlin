package com.hdk24.basemvvm.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import com.hdk24.basemvvm.R
import com.hdk24.basemvvm.databinding.ActivityMainBinding
import com.hdk24.basemvvm.extension.setVisibility
import com.hdk24.basemvvm.extension.showBackArrow
import com.hdk24.basemvvm.presentation.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(),
    NavController.OnDestinationChangedListener {

    private lateinit var navController: NavController

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onViewReady(savedInstance: Bundle?) {
        initView()
    }

    private fun initView() {

        // setup navigation controller
        navController = Navigation.findNavController(this, R.id.mainContent)
        navController.addOnDestinationChangedListener(this)

        // setup toolbar
        setSupportActionBar(binding.mainToolbar)
        setupActionBarWithNavController(navController)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        args: Bundle?
    ) {
        invalidateOptionsMenu()
        when (destination.id) {
            R.id.movieFragment -> {
                binding.mainToolbar.setVisibility(true)
                supportActionBar?.showBackArrow(false)
            }
            else -> {
                binding.mainToolbar.setVisibility(false)
                supportActionBar?.showBackArrow(false)
            }
        }
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.movieFragment -> finish()
            else -> navController.navigateUp()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return true
    }
}
