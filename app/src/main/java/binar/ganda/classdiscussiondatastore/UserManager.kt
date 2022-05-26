package binar.ganda.classdiscussiondatastore

import android.content.Context
import android.preference.Preference
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context : Context) {

    private val dataStore : DataStore<Preferences> = context.createDataStore("user_pref")

    companion object {
        val USERNAME = preferencesKey<String>("USER_USERNAME")
        val PASSWORD = preferencesKey<String>("USER_PASSWORD")
        val NAMA = preferencesKey<String>("USER_PASSWORD")
        val UMUR = preferencesKey<String>("USER_PASSWORD")
        val IMAGE = preferencesKey<String>("USER_PASSWORD")
        val ADDRESS = preferencesKey<String>("USER_PASSWORD")
        val ID = preferencesKey<String>("USER_PASSWORD")


    }

    suspend fun saveData(username: String, password : String, nama: String, umur : String, image : String, address : String, id : String){
        dataStore.edit {
            it[USERNAME] = username
            it[PASSWORD] = password
            it[NAMA] = password
            it[UMUR] = password
            it[IMAGE] = password
            it[ADDRESS] = password
            it[ID] = password
        }
    }

    val userUsername : Flow<String> = dataStore.data.map {
        it[USERNAME] ?: ""
    }

    val userPassword : Flow<String> = dataStore.data.map {
        it[PASSWORD] ?: ""
    }
    val userName : Flow<String> = dataStore.data.map {
        it[NAMA] ?: ""
    }
    val userUmur : Flow<String> = dataStore.data.map {
        it[UMUR] ?: ""
    }
    val userImage : Flow<String> = dataStore.data.map {
        it[IMAGE] ?: ""
    }
    val userAddress : Flow<String> = dataStore.data.map {
        it[ADDRESS] ?: ""
    }
    val userID : Flow<String> = dataStore.data.map {
        it[ID] ?: ""
    }

}