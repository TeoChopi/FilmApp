package com.openwebinars.filmapp.view.fav

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.openwebinars.filmapp.R
import com.openwebinars.filmapp.base.Base
import com.openwebinars.filmapp.databinding.FragmentFavsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavsFragment : Base.BaseFragment<FragmentFavsBinding>(R.layout.fragment_favs) {

    private val favsViewModel: FavsViewModel by activityViewModels()

    override fun init() {
        favsViewModel.onViewCreated()
        favsViewModel.favsLiveData.observe(viewLifecycleOwner) { films ->
            with(binding.recyclerFavs) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = FavsAdapter(films)
            }
        }

        favsViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.pbLoading.isVisible = isLoading
        }
    }

    override fun getViewBinding() = FragmentFavsBinding.inflate(layoutInflater)
}