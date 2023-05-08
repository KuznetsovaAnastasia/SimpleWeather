package com.github.skytoph.simpleweather.presentation.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.core.presentation.menu.MenuItemExpandListener
import com.github.skytoph.simpleweather.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainContentViewModel, FragmentMainBinding>() {

    override val viewModel by viewModels<MainContentViewModel>()

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    private lateinit var searchMenuItem: MenuItem
    private lateinit var menuItemExpandListener: MenuItemExpandListener
    private lateinit var toolbar: Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = binding.homeToolbar.toolbar
        toolbar.inflateMenu(R.menu.home_menu)

        searchMenuItem = toolbar.menu.findItem(R.id.action_search)

        searchView = searchMenuItem.actionView as SearchView
        setupSearchView()

        menuItemExpandListener = MenuItemExpandListener(expand = {
            toolbar.hideMenu()
            viewModel.showSearch()
        }, collapse = {
            toolbar.showMenu()
            viewModel.goBack()
        })

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (searchMenuItem.isActionViewExpanded && !searchView.isFocused)
                        searchMenuItem.collapseActionView()
                    else {
                        viewModel.goBack()
                        isEnabled = false
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }
                }
            })

        toolbar.menu.findItem(R.id.action_settings).setOnMenuItemClickListener {
            viewModel.showSettings(R.id.fragment_container)
            true
        }

        viewModel.observe(viewLifecycleOwner) { state ->
            state.show(childFragmentManager, R.id.weather_fragment_container)
        }
    }

    override fun onResume() {
        super.onResume()
        searchMenuItem.setOnActionExpandListener(menuItemExpandListener)
        if (searchMenuItem.isActionViewExpanded) toolbar.hideMenu()
    }

    override fun onPause() {
        super.onPause()
        searchMenuItem.setOnActionExpandListener(null)
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
    }

    private fun Toolbar.showMenu() {
        this.updatePadding(0, 0, 0, 0)
        this.menu.showAll()
    }

    private fun Toolbar.hideMenu() {
        val toolbarPadding = resources.getDimensionPixelSize(R.dimen.toolbar_collapsed_end_padding)
        this.updatePadding(0, 0, toolbarPadding, 0)
        this.menu.hideExcept(R.id.action_search)
    }

    private fun Menu.hideExcept(id: Int) {
        forEach { item -> if (item.itemId != id) item.isVisible = false }
    }

    private fun Menu.showAll() {
        forEach { item -> item.isVisible = true }
    }
}