package com.github.skytoph.simpleweather.presentation.weather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.skytoph.simpleweather.app.WeatherApp
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.databinding.FragmentWeatherBinding
import com.github.skytoph.simpleweather.presentation.weather.WeatherViewModel
import com.github.skytoph.simpleweather.presentation.weather.adapter.WarningAdapter
import com.github.skytoph.simpleweather.presentation.weather.adapter.WarningLayoutManager

class WeatherFragment : BaseFragment<WeatherViewModel, FragmentWeatherBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as WeatherApp).weatherViewModel
    }

    override fun binding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentWeatherBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity().application as WeatherApp).weatherViewModel

        val warningRecyclerView = binding.warningsContainer

        val adapter = WarningAdapter()

        warningRecyclerView.adapter = adapter
        warningRecyclerView.layoutManager = WarningLayoutManager(context)

        viewModel.observe(this) { weather ->
            binding.apply {
                weather.show(locationView, indicatorsView, sunriseSunsetView, adapter, messageView)
            }
        }
        viewModel.getWeather()
    }
}