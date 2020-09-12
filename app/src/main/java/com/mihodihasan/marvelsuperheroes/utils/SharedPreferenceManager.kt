package com.mihodihasan.marvelsuperheroes.utils


import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(private val preferences: SharedPreferences) {
    fun saveString(key: String, `val`: String?): Boolean {
        preferences.edit().putString(key, `val`).apply()
        return true
    }

    fun saveInt(key: String, `val`: Int): Boolean {
        preferences.edit().putInt(key, `val`).apply()
        return true
    }

    fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    fun getInt(key: String): Int {
        return preferences.getInt(key, -1)
    }

}
