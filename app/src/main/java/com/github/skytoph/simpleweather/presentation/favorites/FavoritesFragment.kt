package com.github.skytoph.simpleweather.presentation.favorites

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.databinding.FragmentFavoritesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override val viewModel by viewModels<FavoritesViewModel>()

    private lateinit var adapter: FavoritesAdapter
    private lateinit var tabLayout: TabLayout

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentFavoritesBinding
        get() = FragmentFavoritesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoritesAdapter(this, viewModel.getFavorites())

        val viewPager = binding.viewPagerFavorites
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2

        tabLayout = requireActivity().findViewById(R.id.tab_layout_dots)
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        val deleteMenuItem =
            requireActivity().findViewById<Toolbar>(R.id.toolbar).menu.findItem(R.id.action_delete)
        deleteMenuItem.setOnMenuItemClickListener {
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
            viewModel.updateState(favorites.isEmpty())
        }

        viewModel.observeState(this) { state ->
            state.show(binding.errorView, parentFragmentManager, tabLayout)
        }

        if (savedInstanceState == null) viewModel.refreshFavorites()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        viewModel.onHiddenChanged(hidden)
    }

    override fun onResume() {
        viewModel.updateChanges()
        PreferenceManager.getDefaultSharedPreferences(context)
            .registerOnSharedPreferenceChangeListener(this)
        super.onResume()
    }

    override fun onPause() {
        PreferenceManager.getDefaultSharedPreferences(context)
            .unregisterOnSharedPreferenceChangeListener(this)
        super.onPause()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) =
        when (key) {
            resources.getString(R.string.key_language) -> {
                val language =
                    sharedPreferences.getString(key, resources.getString(R.string.language_eng))
                val locale = LocaleListCompat.forLanguageTags(language)
                AppCompatDelegate.setApplicationLocales(locale)
                viewModel.updateLocations()
            }
            resources.getString(R.string.key_units), resources.getString(R.string.key_time) ->
                viewModel.updateChanges()
            else -> Unit
        }
}
