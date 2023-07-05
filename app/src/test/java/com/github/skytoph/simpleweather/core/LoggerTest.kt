package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.core.exception.EmptyRequestException
import com.github.skytoph.simpleweather.core.exception.NoResultsException
import org.junit.Test

class LoggerTest {

    private val logger = object : Logger.Abstract() {
        var ignored: Boolean? = null

        override fun log(tag: String, exception: Exception) {
            ignored = isIgnored(exception)
        }
    }

    @Test
    fun test_ignore_list() {
        logger.log("", EmptyRequestException())
        assert(logger.ignored == true)

        logger.log("", NoResultsException())
        assert(logger.ignored == true)

        logger.log("", IllegalStateException())
        assert(logger.ignored == false)
    }
}