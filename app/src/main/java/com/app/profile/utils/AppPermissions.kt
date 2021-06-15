package com.app.profile.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class AppPermissions {

    fun checkAppPermissions(context: Context) {
        val permission = ContextCompat.checkSelfPermission(
            context,
            AppConstants.PERMISSION_CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("TAG", "Permission to record denied")
            makeRequest(context)
        }
    }

    private fun makeRequest(context: Context) {
        ActivityCompat.requestPermissions(
            context as AppCompatActivity,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ),
            AppConstants.STORAGE_REQUEST_CODE
        )
    }
}