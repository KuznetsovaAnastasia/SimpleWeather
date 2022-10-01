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
import com.github.skytoph.simpleweather.presentation.addlocation.AddLocationViewModel.Companion.FAVORITE_KEY
import com.github.skytoph.simpleweather.presentation.addlocation.AddLocationViewModel.Companion.PLACE_ID_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddLocationFragment :
    BaseFragment<AddLocationViewModel, FragmentAddLocationBinding>() {

    override val viewModel by viewModels<AddLocationViewModel>()

    override val bindingInflation: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddLocationBinding =
        FragmentAddLocationBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showWeather(R.id.weather_add_container)

        val button = binding.messageButton
        val favorite = requireArguments().getBoolean(FAVORITE_KEY)
        if (favorite) {
            State.Favorite.show(button, binding.errorView, binding.weatherAddContainer)
        } else {
            viewModel.observeState(this) { state ->
                state.show(button, binding.errorView, binding.weatherAddContainer)
            }
            button.setOnClickListener {
                viewModel.saveWeather()
            }
        }
    }

    companion object {
        fun newInstance(placeId: String, favorite: Boolean): AddLocationFragment =
            AddLocationFragment().apply {
                arguments = bundleOf(PLACE_ID_KEY to placeId, FAVORITE_KEY to favorite)
            }
    }
}