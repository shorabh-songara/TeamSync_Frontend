package com.microland.hackathon.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.microland.hackathon.data.models.authDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenManager(private val context: Context) {

    companion object {
        val TOKEN_KEY = stringPreferencesKey("jwt_token")
    }

    suspend fun saveToken(token: String) {
        context.authDataStore.edit {
            it[TOKEN_KEY] = token
        }
    }

    val getToken: Flow<String?> =
        context.authDataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
}