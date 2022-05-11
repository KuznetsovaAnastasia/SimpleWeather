package com.github.skytoph.simpleweather.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.skytoph.simpleweather.databinding.FragmentMainForecastBinding

class ForecastMainFragment : Fragment() {
    private lateinit var binding: FragmentMainForecastBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainForecastBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}