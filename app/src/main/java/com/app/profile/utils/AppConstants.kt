package com.app.profile.utils

import android.Manifest

class AppConstants {

    companion object {
        // Database
        const val DATABASE_NAME = "profile.db"

        // Click Listener
        const val CLICK_LOGIN = 0
        const val CLICK_TEXT_SIGN_UP = 1
        const val CLICK_TEXT_SIGN_IN = 2
        const val CLICK_SIGN_UP = 3
        const val CLICK_UPDATE_PROFILE = 4

        // Runtime Permissions
        const val PERMISSION_CAMERA = Manifest.permission.CAMERA
        const val PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE

        // Runtime Permissions Request Code
        const val STORAGE_REQUEST_CODE = 101
        const val CAMERA_REQUEST_CODE = 102

        const val DEFAULT_PASSWORD_LENGTH = 8
    }
}