package com.abiramee.codeforceshelper.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class DataStorePreference(private val context: Context) {
    val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
        name = "user_pref"
    )

    companion object{
        val USER_NAME = stringPreferencesKey(name = "USER_NAME")
    }

    suspend fun saveString(key: Preferences.Key<String>, data: String){
        context.dataStore.edit { preferences ->
            preferences[key] = data
        }
    }

    suspend fun read(key: Preferences.Key<String>): String {
        val preferences = context.dataStore.data.first()
        return preferences[key]  ?: "default"

    }
}