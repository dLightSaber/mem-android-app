package com.app.profile

import android.app.Application
import com.app.profile.data.database.ProfileDatabase
import com.app.profile.data.preference.PreferenceManager

class ProfileApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        PreferenceManager.getInstance(this).init()
    }

    fun getRoomDatabase(): ProfileDatabase {
        return ProfileDatabase.getDatabase(this)
    }

    companion object {
        @get:Synchronized
        var instance: ProfileApplication? = null
            private set
    }
}