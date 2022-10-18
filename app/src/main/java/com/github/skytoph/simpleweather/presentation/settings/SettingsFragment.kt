package com.github.skytoph.simpleweather.presentation.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.github.skytoph.simpleweather.R

class SettingsFragment : PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

}