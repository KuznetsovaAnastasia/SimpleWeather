package com.github.skytoph.simpleweather.core.data

import io.realm.Realm
import io.realm.RealmObject

class DataBase(val realm: Realm) {

    inline fun <reified T : RealmObject> createObject(id: String): T =
        realm.createObject(T::class.java, id)
}