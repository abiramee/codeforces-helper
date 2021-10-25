package com.abiramee.codeforceshelper.di

import dagger.hilt.android.HiltAndroidApp
import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

@HiltAndroidApp
class Application: Application() {
    val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
        name = "name"
    )
}