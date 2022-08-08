package com.github.skytoph.simpleweather.core.data

import io.realm.Realm
import io.realm.RealmObject

interface DataBase<T : RealmObject> {

    fun createObject(id: String): T

    abstract class Abstract<T : RealmObject>(private val realm: Realm) : DataBase<T> {
        override fun createObject(id: String): T = realm.createObject(dbClass(), id)
        protected abstract fun dbClass(): Class<T>
    }
}
