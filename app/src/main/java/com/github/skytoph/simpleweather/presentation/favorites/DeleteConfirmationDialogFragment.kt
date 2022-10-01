package com.github.skytoph.simpleweather.presentation.favorites

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.github.skytoph.simpleweather.R
import java.io.Serializable

class DeleteConfirmationDialogFragment : DialogFragment() {

    @Suppress("UNCHECKED_CAST")
    private val callback: () -> Unit by lazy {
        requireArguments().getSerializable(CALLBACK_KEY) as? () -> Unit ?: {}
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.delete_location))
            .setPositiveButton(getString(R.string.confirm)) { _, _ -> callback() }
            .setNegativeButton(getString(R.string.dismiss)) { _, _ -> dismiss() }
            .setCancelable(true)
            .create()

    companion object {
        const val TAG = "DeleteConfirmationDialog"
        const val CALLBACK_KEY = "DeleteConfirmationDialog"

        fun newInstance(callback: () -> Unit) = DeleteConfirmationDialogFragment().apply {
            arguments = bundleOf(CALLBACK_KEY to callback as Serializable)
        }
    }
}