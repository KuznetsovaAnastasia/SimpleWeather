package com.github.skytoph.simpleweather.presentation.favorites

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.os.LocaleListCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import androidx.viewpager2.widget.ViewPager2
import com.facebook.shimmer.ShimmerFrameLayout
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
    private lateinit var viewPager: ViewPager2

    private lateinit var request: ActivityResultLauncher<Array<String>>

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentFavoritesBinding
        get() = FragmentFavoritesBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreferenceManager.getDefaultSharedPreferences(context)
            .registerOnSharedPreferenceChangeListener(this)
        request =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                if (permissions.get(Manifest.permission.ACCESS_FINE_LOCATION) == true)
                    viewModel.saveCurrentLocation()
                else viewModel.showEmptyState()
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        val deleteMenuItem =
            activity.findViewById<Toolbar>(R.id.toolbar).menu.findItem(R.id.action_delete)

        viewModel.observeState(viewLifecycleOwner) { stateList ->
            stateList.forEach { state ->
                state.show(
                    binding.errorView,
                    parentFragmentManager,
                    deleteMenuItem,
                    binding.shimmerFavorites,
                    tabLayout
                ) {
                    if (locationPermissionGranted()) viewModel.saveCurrentLocation()
                    else request.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
                }
            }
        }

        viewModel.initialize(savedInstanceState == null) { favorites ->
            adapter = FavoritesAdapter(this, favorites)
        }
        deleteMenuItem?.setOnMenuItemClickListener {
            viewModel.delete(adapter.getItem(tabLayout.selectedTabPosition))
            true
        }

        viewPager = binding.viewPagerFavorites
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1

        tabLayout = activity.findViewById(R.id.tab_layout_dots)
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        binding.refresh.setOnRefreshListener {
            viewModel.refresh(adapter.itemCount == 0) {
                binding.refresh.isRefreshing = false
            }
        }

        viewModel.observe(this) { favorites ->
            adapter.submitList(favorites)
            viewModel.updateState(favorites.isEmpty())
        }

        viewPager.doOnPreDraw { viewPager.setCurrentItem(viewModel.savedPage(), false) }
    }

    override fun onResume() {
        viewModel.updateChanges()
        super.onResume()
    }

    override fun onPause() {
        viewModel.saveCurrentPage(viewPager.currentItem)
        super.onPause()
    }

    override fun onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(context)
            .unregisterOnSharedPreferenceChangeListener(this)
        super.onDestroy()
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

    private fun locationPermissionGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}