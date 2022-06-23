package com.github.skytoph.simpleweather.core

interface Mappable<T, M : Mapper<T>> {
    fun map(mapper: M): T
}

interface MappableTo<T> {
    fun map(): T
}

interface MappableToDomain<T, M : Mapper<T>> {
    fun map(mapper: M): T
}

interface Mapper<T>