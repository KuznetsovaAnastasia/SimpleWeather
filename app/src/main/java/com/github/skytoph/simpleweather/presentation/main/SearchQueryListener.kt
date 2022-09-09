package com.github.skytoph.simpleweather.presentation.main

import androidx.appcompat.widget.SearchView

class SearchQueryListener(private val search: (String) -> Unit) : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean {
        search.invoke(newText ?: "")
        return false
    }
}