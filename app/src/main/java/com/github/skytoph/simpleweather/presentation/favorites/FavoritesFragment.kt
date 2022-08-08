package com.github.skytoph.simpleweather.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.skytoph.simpleweather.app.WeatherApp
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.databinding.FragmentFavoritesBinding
import com.google.android.material.tabs.TabLayoutMediator


class FavoritesFragment : BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>() {

    override val viewModel by lazy {
        (requireActivity().application as WeatherApp).favoritesViewModel
    }

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentFavoritesBinding
        get() = FragmentFavoritesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = binding.viewPagerFavorites
        val tabLayout = binding.tabLayoutDots
        val adapter = FavoritesAdapter(this, FavoritesAdapter.StringDiffCallback())
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            //TODO handle swipes
        }.attach()

        viewModel.observe(this) { favorites ->
            adapter.submitList(favorites)
        }
    }

    override fun onResume() {
        viewModel.getFavorites()
        super.onResume()
    }
}