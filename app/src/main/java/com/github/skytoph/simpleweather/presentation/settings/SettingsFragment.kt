package com.github.skytoph.simpleweather.presentation.settings

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.presentation.favorites.ConfirmationDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel by viewModels<SettingsViewModel>()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        setupClearHistoryPreference()
    }

    private fun setupClearHistoryPreference() {
        preferenceScreen.findPreference<Preference>(getString(R.string.key_clear_search_history))
            ?.setOnPreferenceClickListener {
                ConfirmationDialogFragment
                    .newInstance({ viewModel.clearSearchHistory() }, R.string.clear_search_history_confirmation)
                    .show(parentFragmentManager, DIALOG_TAG)
                true
            }
    }

    private companion object {
        const val DIALOG_TAG = "ClearHistoryConfirmationDialog"
    }
}