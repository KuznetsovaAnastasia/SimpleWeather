package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.core.data.DataBase
import io.realm.RealmObject

interface Mappable<T, M : Mapper<T>> {
    fun map(mapper: M): T
    interface Base
}

interface MappableTo<T> {
    fun map(): T
}

interface MappableToDB<T : RealmObject, M : Mapper<T>> {
    interface Base<T : RealmObject, M : Mapper<T>> : MappableToDB<T, M> {
        fun map(mapper: M, dataBase: DataBase): T
    }

    interface Embedded<T : RealmObject, M : Mapper<T>> : MappableToDB<T, M> {
        fun map(mapper: M): T
    }
}

interface Mapper<T>