package com.github.skytoph.simpleweather.domain.service.update

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        context.startService(Intent(context, UpdateForecastsService::class.java))
        Log.d(TAG, "receiver called")
    }

    companion object {
        const val REQUEST_CODE = 125345345
        const val TAG = "ErrorTag"
    }
}