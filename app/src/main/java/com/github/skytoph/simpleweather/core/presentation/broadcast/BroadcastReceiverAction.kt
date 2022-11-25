package com.github.skytoph.simpleweather.core.presentation.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BroadcastReceiverAction(private val action: () -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        action()
    }
}