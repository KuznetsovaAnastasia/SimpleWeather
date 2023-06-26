package com.github.skytoph.simpleweather.core.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject
import javax.inject.Singleton

interface RealmProvider {

    fun provide(): Realm

    @Singleton
    class Base @Inject constructor(@ApplicationContext context: Context) : RealmProvider {
        init {
            Realm.init(context)
            val config = getConfig()
            Realm.setDefaultConfiguration(config)
        }

        private fun getConfig(): RealmConfiguration = RealmConfiguration.Builder()
            .build()

        override fun provide(): Realm = Realm.getDefaultInstance()
    }
}
