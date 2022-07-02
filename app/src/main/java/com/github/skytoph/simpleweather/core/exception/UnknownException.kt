package com.github.skytoph.simpleweather.core.exception

import java.lang.Exception

class UnknownException : Exception() {
    override val message: String
        get() = "something went wrong"
}
