package com.github.skytoph.simpleweather.presentation.about

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.skytoph.simpleweather.core.presentation.BaseBindingFragment
import com.github.skytoph.simpleweather.databinding.FragmentSearchBinding

class AboutFragment : BaseBindingFragment<FragmentSearchBinding>() {

    override val bindingInflation:
                (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
}