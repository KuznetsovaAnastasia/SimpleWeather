package com.github.skytoph.simpleweather.presentation.favorites

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

interface PermissionRequest {
    fun register(fragment: Fragment)
    fun request(context: Context)

    class Base(private val permissionType: String, private val handle: HandlePermission) :
        PermissionRequest {

        private lateinit var request: ActivityResultLauncher<Array<String>>

        override fun register(fragment: Fragment) {
            request =
                fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                    if (permissions[permissionType] == true)
                        handle.onGranted()
                }
        }

        override fun request(context: Context) {
            val permission = ContextCompat.checkSelfPermission(context, permissionType)
            if (permission == PackageManager.PERMISSION_GRANTED) handle.onGranted()
            else request.launch(arrayOf(permissionType))
        }
    }
}

fun interface HandlePermission {
    fun onGranted()
}