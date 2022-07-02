package com.github.skytoph.simpleweather.presentation.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.skytoph.simpleweather.databinding.FragmentWeatherListBinding
import com.github.skytoph.simpleweather.presentation.weather.fragment.WeatherFragment
import com.google.android.material.tabs.TabLayoutMediator


class WeatherListFragment : Fragment() {

    private lateinit var binding: FragmentWeatherListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWeatherListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = binding.viewPagerForecast
        val tabLayout = binding.tabLayoutDots
        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3//viewmodel.getSize
            override fun createFragment(position: Int): Fragment =
                WeatherFragment()//viewmodel.getLocation(position)
        }
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            //TODO handle swipes
        }.attach()
    }
}