package com.github.skytoph.simpleweather.core.data

import io.realm.Realm
import io.realm.RealmObject

class DataBase(val realm: Realm) {

    inline fun <reified T : RealmObject> createObject(id: String): T =
        realm.createObject(T::class.java, id)

    inline fun <reified T : RealmObject> createEmbeddedObject(
        parent: RealmObject,
        property: String,
    ): T = realm.createEmbeddedObject(T::class.java, parent, property)

    inline fun <reified T : RealmObject> findMax(fieldName: String): Int =
        realm.where(T::class.java).max(fieldName)?.toInt() ?: 0
}