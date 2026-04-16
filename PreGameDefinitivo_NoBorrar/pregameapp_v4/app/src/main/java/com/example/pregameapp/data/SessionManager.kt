package com.example.pregameapp.data

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("pregame_session", Context.MODE_PRIVATE)

    fun saveSession(userId: Int, username: String, email: String) {
        prefs.edit().apply {
            putInt("user_id", userId)
            putString("username", username)
            putString("email", email)
            putBoolean("logged_in", true)
            apply()
        }
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean("logged_in", false)

    fun getUsername(): String = prefs.getString("username", "") ?: ""
    fun getEmail(): String    = prefs.getString("email", "") ?: ""
    fun getUserId(): Int      = prefs.getInt("user_id", -1)
}
