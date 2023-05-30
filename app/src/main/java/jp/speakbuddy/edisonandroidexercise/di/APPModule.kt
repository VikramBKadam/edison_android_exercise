package jp.speakbuddy.edisonandroidexercise.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.FACT_DATA_STORE
import jp.speakbuddy.edisonandroidexercise.BuildConfig
import jp.speakbuddy.edisonandroidexercise.repository.APIRepository
import jp.speakbuddy.edisonandroidexercise.repository.localStorage.FactDataStore
import jp.speakbuddy.edisonandroidexercise.repository.network.FactAPIService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = FACT_DATA_STORE)

@Module
@InstallIn(SingletonComponent::class)
class APPModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return appContext.dataStore
    }

    @Provides
    @Singleton
    fun provideFactDataStore(dataStore: DataStore<Preferences>): FactDataStore {
        return FactDataStore(dataStore)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.FACT_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): FactAPIService =
        retrofit.create(FactAPIService::class.java)

    @Provides
    @Singleton
    fun provideAPIRepository(apiService: FactAPIService, factDataStore: FactDataStore) =
        APIRepository(apiService = apiService, factDataStore = factDataStore)

    @Provides
    @Singleton
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher
