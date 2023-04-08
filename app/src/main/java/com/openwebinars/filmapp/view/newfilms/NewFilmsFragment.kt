package com.openwebinars.filmapp.view.newfilms

import android.content.Intent
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.openwebinars.filmapp.R
import com.openwebinars.filmapp.base.Base
import com.openwebinars.filmapp.databinding.FragmentNewFilmsBinding
import com.openwebinars.filmapp.view.detail.DetailActivity
import com.openwebinars.filmapp.view.detail.DetailFragment.Companion.EXTRA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewFilmsFragment : Base.BaseFragment<FragmentNewFilmsBinding>(R.layout.fragment_new_films) {

    private val newsViewModel: NewsViewModel by activityViewModels()

    override fun init() {
        newsViewModel.getNews()
        newsViewModel.newsLiveData.observe(viewLifecycleOwner) { films ->
            with(binding.recyclerNews) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = NewFilmsAdapter(films) {
                    val intentDetail = Intent(context, DetailActivity::class.java)
                    intentDetail.putExtra(EXTRA, it)
                    startActivity(intentDetail)
                }
            }
        }

        newsViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.pbLoading.isVisible = isLoading
        }
    }

    override fun getViewBinding() = FragmentNewFilmsBinding.inflate(layoutInflater)
}