package com.github.skytoph.simpleweather.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.skytoph.simpleweather.app.WeatherApp
import com.github.skytoph.simpleweather.databinding.FragmentSearchResultBinding
import com.github.skytoph.simpleweather.presentation.search.adapter.SearchLocationAdapter
import com.github.skytoph.simpleweather.presentation.search.viewmodel.SearchPredictionViewModel

class SearchResultFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultBinding
    private lateinit var viewModel: SearchPredictionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (requireActivity().application as WeatherApp).searchPredictionViewModel
        val adapter = SearchLocationAdapter()
        binding.searchPredictionRecyclerView.adapter = adapter
        viewModel.observe(this) { locations ->
            adapter.submitList(locations.toMutableList())
        }
    }
}