package com.github.skytoph.simpleweather.di.favorites

import androidx.recyclerview.widget.DiffUtil
import com.github.skytoph.simpleweather.presentation.favorites.FavoritesAdapter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class FavoritesAdapterModule {

    @Binds
    abstract fun diffCallback(callback: FavoritesAdapter.StringDiffCallback): DiffUtil.ItemCallback<String>
}