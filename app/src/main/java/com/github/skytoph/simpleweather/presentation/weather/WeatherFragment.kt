package com.github.skytoph.simpleweather.presentation.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.github.skytoph.simpleweather.app.WeatherApp
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.databinding.FragmentWeatherBinding
import com.github.skytoph.simpleweather.presentation.weather.adapter.WarningAdapter
import com.github.skytoph.simpleweather.presentation.weather.adapter.WarningLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment :
    BaseFragment<WeatherViewModel, FragmentWeatherBinding>() {

    override val viewModel by viewModels<WeatherViewModel>()

    private lateinit var locationId: String
    private var favorite: Boolean = true

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentWeatherBinding
        get() = FragmentWeatherBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().apply {
            locationId = getString(PLACE_ID_KEY, "")
            favorite = getBoolean(FAVORITE_KEY)
        }
    }

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
        viewModel.getWeather(locationId, favorite)
    }

    override fun onPause() {
        viewModel.saveWeather(favorite)
        super.onPause()
    }

    companion object {
        private const val PLACE_ID_KEY = "placeId"
        private const val FAVORITE_KEY = "favorite"

        fun newInstance(placeId: String, favorite: Boolean): WeatherFragment =
            WeatherFragment().apply {
                arguments = bundleOf(PLACE_ID_KEY to placeId, FAVORITE_KEY to favorite)
            }
    }
}