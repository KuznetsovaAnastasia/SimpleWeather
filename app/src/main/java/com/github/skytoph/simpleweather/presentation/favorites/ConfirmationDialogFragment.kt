package com.github.skytoph.simpleweather.presentation.favorites

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.github.skytoph.simpleweather.R
import java.io.Serializable

class ConfirmationDialogFragment : DialogFragment() {

    @Suppress("UNCHECKED_CAST", "DEPRECATION")
    private val callback: () -> Unit by lazy {
        requireArguments().getSerializable(CALLBACK_KEY) as? () -> Unit ?: {}
    }
    private val messageId by lazy { requireArguments().getInt(MESSAGE_ID_KEY) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(messageId))
            .setPositiveButton(getString(R.string.confirm)) { _, _ -> callback() }
            .setNegativeButton(getString(R.string.dismiss)) { _, _ -> dismiss() }
            .setCancelable(true)
            .create()

    companion object {
        const val CALLBACK_KEY = "key_callback"
        const val MESSAGE_ID_KEY = "key_message_id_res"

        fun newInstance(callback: () -> Unit, @StringRes messageId: Int) =
            ConfirmationDialogFragment().apply {
                arguments = bundleOf(CALLBACK_KEY to callback as Serializable,
                    MESSAGE_ID_KEY to messageId)
            }
    }
}