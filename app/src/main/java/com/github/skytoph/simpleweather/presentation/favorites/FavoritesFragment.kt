package com.github.skytoph.simpleweather.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.databinding.FragmentFavoritesBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>() {

    override val viewModel by viewModels<FavoritesViewModel>()

    @Inject
    lateinit var adapter: FavoritesAdapter

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentFavoritesBinding
        get() = FragmentFavoritesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewPagerFavorites.adapter = adapter
            TabLayoutMediator(tabLayoutDots, viewPagerFavorites) { _, _ -> }.attach()
        }
        viewModel.observe(this) { favorites ->
            adapter.submitList(favorites)
        }
    }

    override fun onResume() {
        viewModel.getFavorites()
        super.onResume()
    }
}