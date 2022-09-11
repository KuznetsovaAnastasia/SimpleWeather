package com.github.skytoph.simpleweather.presentation.addlocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.databinding.FragmentAddLocationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddLocationFragment :
    BaseFragment<AddLocationViewModel, FragmentAddLocationBinding>() {

    override val viewModel by viewModels<AddLocationViewModel>()

    private lateinit var locationId: String
    private var favorite: Boolean = false

    override val bindingInflation: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddLocationBinding =
        FragmentAddLocationBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationId = requireArguments().getString(PLACE_ID_KEY, "")
        favorite = requireArguments().getBoolean(FAVORITE_KEY, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = binding.messageButton
        viewModel.showWeather(R.id.weather_add_container, locationId) { fav ->
            if (fav) button.setClickedStyle()
            else button.setOnClickListener {
                viewModel.saveWeather()
            }
            button.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val PLACE_ID_KEY = "placeId"
        private const val FAVORITE_KEY = "favorite"

        fun newInstance(placeId: String, favorite: Boolean): AddLocationFragment =
            AddLocationFragment().apply {
                arguments = bundleOf(PLACE_ID_KEY to placeId, FAVORITE_KEY to favorite)
            }
    }
}