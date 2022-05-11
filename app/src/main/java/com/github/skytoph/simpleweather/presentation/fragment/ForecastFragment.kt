package com.github.skytoph.simpleweather.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.skytoph.simpleweather.databinding.FragmentForecastBinding

class ForecastFragment : Fragment() {
    private lateinit var binding: FragmentForecastBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}