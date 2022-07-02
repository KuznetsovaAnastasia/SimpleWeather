package com.github.skytoph.simpleweather.presentation.addlocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.skytoph.simpleweather.databinding.FragmentAddLocationBinding

class AddLocationFragment : Fragment() {
    private lateinit var binding: FragmentAddLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddLocationBinding.inflate(inflater, container, false)
        return binding.root
    }
}