package com.github.skytoph.simpleweather.data.favorites.cache

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.favorites.FavoriteData
import com.github.skytoph.simpleweather.data.favorites.mapper.FavoriteDataMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FavoriteDB : RealmObject(), Mappable<FavoriteData, FavoriteDataMapper> {
    @PrimaryKey
    var id: String = ""
    var name: String = ""

    override fun map(mapper: FavoriteDataMapper) = mapper.map(id, name)
}
