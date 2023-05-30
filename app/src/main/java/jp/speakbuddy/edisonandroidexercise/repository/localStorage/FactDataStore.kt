package jp.speakbuddy.edisonandroidexercise.repository.localStorage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.FACT_KEY_STRING
import jp.speakbuddy.edisonandroidexercise.model.Fact
import kotlinx.coroutines.flow.first

class FactDataStore(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val FACT_KEY = stringPreferencesKey(FACT_KEY_STRING)
    }

    suspend fun saveFact(fact: Fact) {
        dataStore.edit { preferences ->
            val json = Gson().toJson(fact)
            preferences[FACT_KEY] = json
        }
    }

    suspend fun getFact(): Fact? {
        val preferences = dataStore.data.first()
        val json = preferences[FACT_KEY] ?: return null
        return Gson().fromJson(json, Fact::class.java)
    }
}
