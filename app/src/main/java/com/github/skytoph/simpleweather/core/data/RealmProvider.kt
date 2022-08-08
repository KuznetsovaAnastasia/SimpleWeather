package com.github.skytoph.simpleweather.core.data

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

interface RealmProvider {

    fun provide(): Realm

    class Base(context: Context) : RealmProvider {
        init {
            Realm.init(context)
            val config = getConfig()
            Realm.setDefaultConfiguration(config)
        }

        private fun getConfig(): RealmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()

        override fun provide(): Realm = Realm.getDefaultInstance()
    }
}
