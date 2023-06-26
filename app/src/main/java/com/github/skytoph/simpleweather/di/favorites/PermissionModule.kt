package com.github.skytoph.simpleweather.di.favorites

import com.github.skytoph.simpleweather.presentation.favorites.PermissionRequest
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
interface PermissionModule {

    @Binds
    @FragmentScoped
    fun permissionRequest(request: PermissionRequest.Base): PermissionRequest
}