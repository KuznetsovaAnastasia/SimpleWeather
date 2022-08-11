package com.github.skytoph.simpleweather.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.skytoph.simpleweather.core.presentation.navigation.MainViewModel
import com.github.skytoph.simpleweather.databinding.ActivityMainBinding
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
        viewModel.observeProgress(this) { visibility ->
            visibility.apply(binding.progressBar)
        }
        viewModel.observeMessages(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}