package com.abiramee.codeforceshelper.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class DataStorePreference(private val context: Context) {
    val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
        name = "user_pref"
    )

    companion object{
        val USER_NAME = stringPreferencesKey(name = "USER_NAME")
        val MONTHLY_COUNT = intPreferencesKey(name ="MONTHLY_COUNT")
        val DAILY_COUNT = intPreferencesKey(name ="DAILY_COUNT")
    }

    suspend fun save(key: Preferences.Key<String>, data: String){
        context.dataStore.edit { preferences ->
            preferences[key] = data
        }
    }
    suspend fun save(key: Preferences.Key<Int>, data: Int){
        context.dataStore.edit { preferences ->
            preferences[key] = data
        }
    }


    suspend fun readString(key: Preferences.Key<String>): String {
        val preferences = context.dataStore.data.first()
        return preferences[key]  ?: ""
    }


    suspend fun readInt(key: Preferences.Key<Int>): Int {
        val preferences = context.dataStore.data.first()
        return preferences[key]  ?: 0
    }
}