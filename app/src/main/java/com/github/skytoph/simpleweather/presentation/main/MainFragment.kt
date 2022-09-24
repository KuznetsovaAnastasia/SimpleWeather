package com.github.skytoph.simpleweather.presentation.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.forEach
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.core.presentation.menu.MenuItemExpandListener
import com.github.skytoph.simpleweather.core.presentation.view.listener.FocusChangeListener
import com.github.skytoph.simpleweather.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainContentViewModel, FragmentMainBinding>() {

    override val viewModel by viewModels<MainContentViewModel>()

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null)
            viewModel.showFavorites(R.id.weather_fragment_container)

        val toolbar = binding.homeToolbar.toolbar
        toolbar.inflateMenu(R.menu.home_menu)

        val searchMenuItem = toolbar.menu.findItem(R.id.action_search)

        searchView = searchMenuItem.actionView as SearchView
        setupSearchView()

        val toolbarPadding = resources.getDimensionPixelSize(R.dimen.toolbar_collapsed_end_padding)

        searchMenuItem.setOnActionExpandListener(MenuItemExpandListener(expand = {
            toolbar.updatePadding(0, 0, toolbarPadding, 0)
            toolbar.menu.hideExcept(R.id.action_search)
            viewModel.showSearch(R.id.weather_fragment_container)
        }, collapse = {
            toolbar.updatePadding(0, 0, 0, 0)
            toolbar.menu.showAll()
            viewModel.goBack()
        }))
    }

    private fun setupSearchView() {
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.findViewById<View?>(R.id.search_bar)
            ?.setBackgroundResource(R.drawable.rectangle_rounded_15)
        searchView.findViewById<View?>(R.id.search_plate)
            ?.setBackgroundColor(Color.TRANSPARENT)
        searchView.setOnQueryTextListener(SearchQueryListener { query ->
            viewModel.getPredictions(query)
        })
        searchView.findViewById<SearchView.SearchAutoComplete?>(R.id.search_src_text).onFocusChangeListener =
            FocusChangeListener(notFocused = {
                if (searchView.query.isNullOrBlank()) viewModel.goBack()
            })
    }

    private fun Menu.hideExcept(id: Int) {
        forEach { item -> if (item.itemId != id) item.isVisible = false }
    }

    private fun Menu.showAll() {
        forEach { item -> item.isVisible = true }
    }
}