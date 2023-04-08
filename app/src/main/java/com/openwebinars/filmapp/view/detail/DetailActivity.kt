package com.openwebinars.filmapp.view.detail

import androidx.fragment.app.Fragment
import com.openwebinars.filmapp.R
import com.openwebinars.filmapp.base.Base
import com.openwebinars.filmapp.databinding.ActivityDetailBinding
import com.openwebinars.filmapp.model.Film
import com.openwebinars.filmapp.view.detail.DetailFragment.Companion.EXTRA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : Base.BaseActivity<ActivityDetailBinding>() {

    override fun init() {
        getExtras()?.let {
            setFragment(DetailFragment.newInstance(it))
        } ?: finish()
    }

    private fun getExtras(): Film? = intent.extras?.getParcelable(EXTRA)

    private fun setFragment(fragmentToChange: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutFragmentHolder, fragmentToChange)
            .commit()
    }

    override fun getViewBinding() = ActivityDetailBinding.inflate(layoutInflater)
}