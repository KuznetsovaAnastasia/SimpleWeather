package com.github.skytoph.simpleweather.core.data

interface SaveItemToCache<T> {
    suspend fun save(source: SaveItem<T>)
}

interface UpdateCachedItem<T, I> {
    suspend fun update(source: UpdateItem<T, I>): T
    suspend fun updateLocation(source: UpdateItem<T, I>): T
}

interface UpdateCachedItemTime<T, I> {
    suspend fun update(source: UpdateItemTime<T, I>)
}

interface Item<T, I> : SaveItemToCache<T>, UpdateCachedItem<T, I>, UpdateCachedItemTime<T, I>

interface CachedItem<T, I> : Item<T, I> {
    fun cache(value: Item<T, I>)
    fun clear()

    class Empty<T, I> : CachedItem<T, I> {
        override suspend fun save(source: SaveItem<T>) {}
        override suspend fun update(source: UpdateItem<T, I>): T =
            throw IllegalStateException("can not update empty item")

        override suspend fun updateLocation(source: UpdateItem<T, I>): T =
            throw IllegalStateException("can not update empty item")

        override suspend fun update(source: UpdateItemTime<T, I>) =
            throw IllegalStateException("can not update empty item")

        override fun cache(value: Item<T, I>) {}
        override fun clear() {}
    }
}

abstract class BaseCache<T, I> : CachedItem<T, I> {
    private var cached: Item<T, I> = CachedItem.Empty()

    override fun cache(value: Item<T, I>) {
        cached = value
    }

    override suspend fun save(source: SaveItem<T>) =
        cached.save(source)

    override suspend fun update(source: UpdateItem<T, I>): T =
        cached.update(source)

    override suspend fun update(source: UpdateItemTime<T, I>) =
        cached.update(source)

    override suspend fun updateLocation(source: UpdateItem<T, I>): T =
        cached.updateLocation(source)

    override fun clear() {
        cached = CachedItem.Empty()
    }
}