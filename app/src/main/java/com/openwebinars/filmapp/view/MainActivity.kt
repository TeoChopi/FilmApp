package com.openwebinars.filmapp.view

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.openwebinars.filmapp.base.Base
import com.openwebinars.filmapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : Base.BaseActivity<ActivityMainBinding>() {

    private val navController: NavController
        get() =
        binding.navHostFragment.getFragment<NavHostFragment>().navController

    override fun init() {
        binding.bottomNavView.setupWithNavController(navController)
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}