package com.github.skytoph.simpleweather.presentation.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.databinding.FragmentWeatherBinding
import com.github.skytoph.simpleweather.presentation.weather.WeatherViewModel.Companion.FAVORITE_KEY
import com.github.skytoph.simpleweather.presentation.weather.WeatherViewModel.Companion.PLACE_ID_KEY
import com.github.skytoph.simpleweather.presentation.weather.adapter.WarningAdapter
import com.github.skytoph.simpleweather.presentation.weather.adapter.WarningLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : BaseFragment<WeatherViewModel, FragmentWeatherBinding>() {

    override val viewModel by viewModels<WeatherViewModel>()

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentWeatherBinding
        get() = FragmentWeatherBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val warningAdapter = WarningAdapter()
        binding.warningsContainer.apply {
            adapter = warningAdapter
            layoutManager = WarningLayoutManager(context)
        }

        viewModel.observe(this) { weather ->
            binding.apply {
                weather.show(locationView,
                    indicatorsView,
                    sunriseSunsetView,
                    warningAdapter,
                    messageView)
            }
        }
        viewModel.observeRefresh(this) { refresh ->
            if (refresh) viewModel.refreshFromCache()
        }
        if (savedInstanceState == null) viewModel.getWeather()
    }

    companion object {
        fun newInstance(placeId: String, favorite: Boolean): WeatherFragment =
            WeatherFragment().apply {
                arguments = bundleOf(PLACE_ID_KEY to placeId, FAVORITE_KEY to favorite)
            }
    }
}