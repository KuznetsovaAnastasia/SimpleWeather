package com.github.skytoph.simpleweather.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.databinding.FragmentSearchResultBinding
import com.github.skytoph.simpleweather.presentation.search.adapter.SearchLocationAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment :
    BaseFragment<SearchPredictionViewModel, FragmentSearchResultBinding>() {

    override val viewModel by viewModels<SearchPredictionViewModel>()

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentSearchResultBinding
        get() = FragmentSearchResultBinding::inflate

    private lateinit var tabLayout: TabLayout
    private lateinit var searchMenuItem: MenuItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SearchLocationAdapter(object : SearchLocationAdapter.LocationClickListener {
            override fun open(id: String, favorite: Boolean) {
                hideKeyboard()
                viewModel.showDetails(R.id.fragment_container, id, favorite)
            }
        })
        binding.searchPredictionRecyclerView.adapter = adapter
        viewModel.observe(this) { locations ->
            adapter.submitList(locations.toMutableList())
        }

        tabLayout = requireActivity().findViewById(R.id.tab_layout_dots)
        searchMenuItem = requireActivity().findViewById<Toolbar>(R.id.toolbar).menu.findItem(R.id.action_search)
    }

    override fun onResume() {
        tabLayout.visibility = View.GONE
        super.onResume()
    }

    override fun onPause() {
        tabLayout.visibility = View.VISIBLE
        searchMenuItem.collapseActionView()
        super.onPause()
    }
}