package com.github.skytoph.simpleweather.core.exception

class UnknownException : Exception() {
    override val message: String
        get() = "something went wrong"
}

class NoCachedDataException : Exception() {
    override val message: String
        get() = "no cached data"
}

class EmptyRequestException : Exception() {
    override val message: String
        get() = "request is empty"
}

class NoResultsException : Exception() {
    override val message: String
        get() = "nothing found"
}
