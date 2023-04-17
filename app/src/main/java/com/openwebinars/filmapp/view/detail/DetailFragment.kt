package com.openwebinars.filmapp.view.detail

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.openwebinars.filmapp.R
import com.openwebinars.filmapp.base.Base
import com.openwebinars.filmapp.databinding.FragmentDetailBinding
import com.openwebinars.filmapp.model.Film
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Base.BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    companion object {
        const val EXTRA = "film"
    }

    private val detailViewModel: DetailViewModel by activityViewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun init() {
        val film = args.film
        showContent(film)
        detailViewModel.onViewCreated(film)
    }

    private fun showContent(film: Film?) {
        film?.let {
            binding.tvTitle.text = it.name
            binding.tvDirector.text = it.director
            binding.tvSynopsis.text = it.synopsis
            showIsFavorite(it)
            binding.ivFav.setOnClickListener {
                binding.ivFav.setColorFilter(Color.parseColor("#FFDAA95E"))
                detailViewModel.onClickFavorite(film)
            }
        }
    }

    private fun showIsFavorite(film: Film) {
        binding.ivFilm.setImageDrawable(getImageSrc(film.image, context))
        detailViewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
                binding.ivFav.setColorFilter(Color.parseColor("#FFDAA95E"))
            } else {
                binding.ivFav.setColorFilter(Color.parseColor("#000000"))
            }
        }
    }

    private fun getImageSrc(name: String, context: Context?): Drawable? {
        return context?.let {
            val resources: Resources = context.resources
            val resourceId: Int = resources.getIdentifier(
                name, "drawable",
                context?.packageName
            )
            resources.getDrawable(resourceId, null)
        }
    }

    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)
}