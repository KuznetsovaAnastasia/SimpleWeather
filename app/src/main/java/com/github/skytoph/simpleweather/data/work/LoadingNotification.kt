package com.github.skytoph.simpleweather.data.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo
import com.github.skytoph.simpleweather.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoadingNotification @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationManager: NotificationManager
) {

    fun createForegroundInfo(): ForegroundInfo {
        val channelID = context.getString(R.string.notification_loading_channel_id)
        val title = context.getString(R.string.notification_loading_title)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(channelID)
        }

        val notification = NotificationCompat.Builder(context, channelID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.weather_sun)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setProgress(0, 0, true)
            .build()

        return ForegroundInfo(NOTIFICATION_ID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(channelID: String) {
        val name = context.getString(R.string.notification_loading_channel_name)
        val descriptionText = context.getString(R.string.notification_loading_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun cancel() {
        notificationManager.cancel(NOTIFICATION_ID)
    }

    private companion object {
        private const val NOTIFICATION_ID = 1001
    }
}