package com.app.profile.data.preference

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager private constructor(context: Context) {

    private lateinit var prefs: SharedPreferences
    private val prefName = "profile_preference"
    private var mContext = context

    fun init() {
        prefs = mContext.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    fun read(key: String, default: Any): Any? {
        when (default) {
            is String -> return prefs.getString(key, default)
            is Boolean -> return prefs.getBoolean(key, default)
            is Long -> return prefs.getLong(key, default)
            is Float -> return prefs.getFloat(key, default)
            is Int -> return prefs.getInt(key, default)
        }
        return null
    }

    fun write(key: String, value: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        with(editor) {
            putString(key, value)
            apply()
        }
    }

    companion object : PreferenceHolder<PreferenceManager, Context>(::PreferenceManager)
}