package com.github.skytoph.simpleweather.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.skytoph.simpleweather.app.WeatherApp
import com.github.skytoph.simpleweather.databinding.FragmentForecastBinding
import com.github.skytoph.simpleweather.presentation.WeatherViewModel
import com.github.skytoph.simpleweather.presentation.adapter.WarningAdapter
import com.github.skytoph.simpleweather.presentation.adapter.WarningLayoutManager

class ForecastFragment : Fragment() {
    private lateinit var binding: FragmentForecastBinding
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity().application as WeatherApp).viewModel

        val warningRecyclerView = binding.warningsContainer

        val adapter = WarningAdapter()

        warningRecyclerView.adapter = adapter
        warningRecyclerView.layoutManager = WarningLayoutManager(context)

        viewModel.observe(this) { state ->
            binding.apply {
                state.show(locationView,
                    indicatorsView,
                    sunriseSunsetView,
                    adapter,
                    messageView) //todo remove adapter
            }
        }

        viewModel.getWeather()
    }
}