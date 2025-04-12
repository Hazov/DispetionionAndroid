package com.example.data.auth

import android.content.Context

class PermissionsManager (private val  context: Context) {
    private val PERMISSIONS_KEY = "permissions"
    private val AUTH_PREFS = "auth"

    fun getPermissions(): Set<String> {
        return context.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE)
            .getStringSet(PERMISSIONS_KEY, HashSet<String>()).orEmpty()
    }

    fun savePermissions(permissions: List<String>) {
        context.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE)
            .edit()
            .putStringSet(PERMISSIONS_KEY, permissions.toSet())
            .apply()

    }

    fun deletePermissions() {
        context.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE)
            .edit()
            .remove(PERMISSIONS_KEY)
            .apply()
    }
}