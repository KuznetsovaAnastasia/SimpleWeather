package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.util.time.CurrentTime

interface CurrentTimeComparable {
    fun updatedLately(mapper: CompareTimeWithCurrent, criteria: UpdatedLately): Boolean
}

sealed class UpdatedLately {
    abstract fun map(time: CurrentTime): ComparableTime

    object LastHour : UpdatedLately() {
        override fun map(time: CurrentTime): ComparableTime = ComparableTime.Hours(time)
    }

    object LastDay : UpdatedLately() {
        override fun map(time: CurrentTime): ComparableTime = ComparableTime.Day(time)
    }

    object Anytime : UpdatedLately() {
        override fun map(time: CurrentTime): ComparableTime = ComparableTime.Any()
    }
}