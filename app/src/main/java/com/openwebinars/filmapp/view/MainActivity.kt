package com.openwebinars.filmapp.view

import androidx.fragment.app.Fragment
import com.openwebinars.filmapp.R
import com.openwebinars.filmapp.base.Base
import com.openwebinars.filmapp.databinding.ActivityMainBinding
import com.openwebinars.filmapp.view.fav.FavsFragment
import com.openwebinars.filmapp.view.newfilms.NewFilmsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : Base.BaseActivity<ActivityMainBinding>() {

    private val newsFragment = NewFilmsFragment()
    private val favsFragment = FavsFragment()

    override fun init() {
        setFragment(newsFragment)
        setViewBottomNavigationListener()
    }

    private fun setFragment(fragmentToChange: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutFragmentHolder, fragmentToChange)
            .commit()
    }

    private fun setViewBottomNavigationListener() {
        binding.viewBottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.news -> setFragment(newsFragment)
                R.id.favs -> setFragment(favsFragment)
            }
            true
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}