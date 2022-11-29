package com.github.skytoph.simpleweather.data.weather.model.content.horizon

interface IsDaytime {
    fun isDaytime(time: Long): Boolean
}