package com.github.skytoph.simpleweather.presentation.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.skytoph.simpleweather.databinding.ActivityMainBinding
import com.github.skytoph.simpleweather.domain.service.update.AlarmReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.observeNavigation(this) { screen ->
            screen.show(supportFragmentManager)
        }
        viewModel.observeProgress(this) { visible ->
            binding.progressBar.visibility = if (visible) View.VISIBLE
            else View.INVISIBLE
        }

        if (savedInstanceState == null) viewModel.showMain()

        scheduleService()
    }

    private fun scheduleService() {
        val pendingIntent = PendingIntent.getBroadcast(applicationContext,
            AlarmReceiver.REQUEST_CODE,
            Intent(applicationContext, AlarmReceiver::class.java),
            PendingIntent.FLAG_MUTABLE)

        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            AlarmManager.INTERVAL_FIFTEEN_MINUTES,
            pendingIntent
        )
    }
}

