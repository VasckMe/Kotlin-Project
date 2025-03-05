package com.example.dsw52839.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDataManager(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("user_info")

    companion object {
        val isLoggedKey = booleanPreferencesKey("is_logged_key")
    }

    val isLogged: Flow<Boolean?> = context.dataStore.data.map { preferences ->
            preferences[isLoggedKey]
        }

    suspend fun saveData(isLogged: Boolean) {
        context.dataStore.edit { preferences -> preferences[isLoggedKey] = isLogged }
    }

}