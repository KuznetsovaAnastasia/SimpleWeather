package com.github.skytoph.simpleweather.domain.weather.mapper

interface CurrentTimeComparable {
    fun updatedLately(mapper: CompareTimeWithCurrent, criteria: DataUpdatedLatelyCriteria): Boolean
}

enum class DataUpdatedLatelyCriteria {
    BASE,
    HOURS,
    DAYS
}