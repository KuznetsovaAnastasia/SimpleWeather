package com.github.skytoph.simpleweather.core.exception

class UnknownException : Exception() {
    override val message: String
        get() = "something went wrong"
}

class NoCachedDataException : Exception() {
    override val message: String
        get() = "no cached data"
}

class DataIsNotCachedException(private val id: String) : Exception() {
    override val message: String
        get() = "can not find data with id $id"
}

class EmptyRequestException : Exception() {
    override val message: String
        get() = "request is empty"
}

class NoResultsException : Exception() {
    override val message: String
        get() = "nothing found"
}

class CanNotUpdateLocationException : Exception() {
    override val message: String
        get() = "can't update location after language change"
}

class FavoritesLimitException(private val limit: Int) : Exception() {
    override val message: String
        get() = "the number of favorite locations has reached the limit of $limit"
}

class RefreshForecastsException : Exception() {
    override val message: String
        get() = "can't update forecast data"
}