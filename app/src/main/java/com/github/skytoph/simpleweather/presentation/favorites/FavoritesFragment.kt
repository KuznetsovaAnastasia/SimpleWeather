package com.github.skytoph.simpleweather.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.databinding.FragmentFavoritesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>() {

    override val viewModel by viewModels<FavoritesViewModel>()

    private lateinit var adapter: FavoritesAdapter
    private lateinit var tabLayout: TabLayout

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentFavoritesBinding
        get() = FragmentFavoritesBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoritesAdapter(this, viewModel.getFavorites())

        val viewPager = binding.viewPagerFavorites
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2

        tabLayout = requireActivity().findViewById(R.id.tab_layout_dots)
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        requireActivity().findViewById<Toolbar>(R.id.toolbar).menu.findItem(R.id.action_delete)
            .setOnMenuItemClickListener {
                viewModel.delete(adapter.getItem(tabLayout.selectedTabPosition))
                true
            }

        binding.refresh.setOnRefreshListener {
            viewModel.refresh {
                binding.refresh.isRefreshing = false
            }
        }

        viewModel.observe(this) { favorites ->
            adapter.submitList(favorites)
        }

        if (savedInstanceState == null) viewModel.refreshFavorites()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) viewModel.refreshFavorites()
    }
}