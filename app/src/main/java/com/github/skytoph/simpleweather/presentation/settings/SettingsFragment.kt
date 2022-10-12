package com.github.skytoph.simpleweather.presentation.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.github.skytoph.simpleweather.R

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onResume() {
        PreferenceManager.getDefaultSharedPreferences(context)
            .registerOnSharedPreferenceChangeListener(this)
        super.onResume()
    }

    override fun onPause() {
        PreferenceManager.getDefaultSharedPreferences(context)
            .unregisterOnSharedPreferenceChangeListener(this)
        super.onPause()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) =
        if (key == resources.getString(R.string.key_language)) {
            val language = sharedPreferences.getString(key, resources.getString(R.string.language_eng))
            val locale = LocaleListCompat.forLanguageTags(language)
            AppCompatDelegate.setApplicationLocales(locale)
        } else Unit
}