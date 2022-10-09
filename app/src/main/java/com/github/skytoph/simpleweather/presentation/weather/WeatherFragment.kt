package com.github.skytoph.simpleweather.presentation.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.core.presentation.MarginItemDecoration
import com.github.skytoph.simpleweather.databinding.FragmentWeatherBinding
import com.github.skytoph.simpleweather.presentation.weather.WeatherViewModel.Companion.FAVORITE_KEY
import com.github.skytoph.simpleweather.presentation.weather.WeatherViewModel.Companion.PLACE_ID_KEY
import com.github.skytoph.simpleweather.presentation.weather.adapter.forecast.DailyForecastAdapter
import com.github.skytoph.simpleweather.presentation.weather.adapter.forecast.HourlyForecastAdapter
import com.github.skytoph.simpleweather.presentation.weather.adapter.warning.NotScrollableLayoutManager
import com.github.skytoph.simpleweather.presentation.weather.adapter.warning.WarningAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : BaseFragment<WeatherViewModel, FragmentWeatherBinding>() {

    private val TAG = "ErrorTag"
    override val viewModel by viewModels<WeatherViewModel>()

    override val bindingInflation: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentWeatherBinding
        get() = FragmentWeatherBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val warningAdapter = WarningAdapter()
        binding.warningsRecyclerview.apply {
            adapter = warningAdapter
            layoutManager = NotScrollableLayoutManager(context)
            addItemDecoration(MarginItemDecoration(spaceBottom = resources.getDimensionPixelSize(R.dimen.warning_item_bottom_margin)))
        }

        val hourlyForecastAdapter = HourlyForecastAdapter()
        binding.forecastHourlyRecyclerview.apply {
            adapter = hourlyForecastAdapter
            hourlyForecastAdapter.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT
            addItemDecoration(MarginItemDecoration(spaceRight = resources.getDimensionPixelSize(R.dimen.forecast_item_right_margin)))
        }

        val dailyForecastAdapter = DailyForecastAdapter()
        binding.forecastWeeklyRecyclerview.apply {
            adapter = dailyForecastAdapter
            layoutManager = NotScrollableLayoutManager(context)
            addItemDecoration(MarginItemDecoration(spaceBottom = resources.getDimensionPixelSize(R.dimen.forecast_item_bottom_margin)))
        }

        viewModel.observe(this) { weather ->
            binding.apply {
                weather.show(locationView,
                    indicatorsView,
                    sunriseSunsetView,
                    forecastWeeklyRecyclerview,
                    submitLists = { warnings, hourly, daily ->
                        warningAdapter.submitList(warnings)
                        hourlyForecastAdapter.submitList(hourly)
                        dailyForecastAdapter.submitList(daily)
                    })
            }
        }
        viewModel.observeRefresh(this) { refresh ->
            if (refresh) viewModel.refreshFromCache()

        }
        viewModel.getWeather(savedInstanceState == null)
    }

    companion object {
        fun newInstance(placeId: String, favorite: Boolean): WeatherFragment =
            WeatherFragment().apply {
                arguments = bundleOf(PLACE_ID_KEY to placeId, FAVORITE_KEY to favorite)
            }
    }
}