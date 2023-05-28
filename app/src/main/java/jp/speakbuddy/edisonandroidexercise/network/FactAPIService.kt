package jp.speakbuddy.edisonandroidexercise.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface FactAPIService {
    @GET("fact/")
    suspend fun getFact(): FactResponse
}

@Serializable
data class FactResponse(
    val fact: String,
    val length: Int
)
