package com.github.skytoph.simpleweather.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.core.presentation.TextEditorWatcher
import com.github.skytoph.simpleweather.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainContentViewModel, FragmentMainBinding>() {
    override val viewModel by viewModels<MainContentViewModel>()

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showFavorites(R.id.weather_fragment_container)

        val searchEditText = binding.editTextSearchLocation

        searchEditText.addTextChangedListener(object : TextEditorWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                search()
            }
        })
        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                viewModel.showSearch(R.id.weather_fragment_container)
                search()
            } else {
                hideKeyboard()
                if (searchEditText.text.isNullOrBlank())
                    viewModel.goBack()
            }
        }

        binding.refresh.setOnRefreshListener {
            viewModel.refresh()
            binding.refresh.isRefreshing = false
        }
    }

    private fun search() {
        viewModel.getPredictions(binding.editTextSearchLocation.text?.toString() ?: "")
    }
}