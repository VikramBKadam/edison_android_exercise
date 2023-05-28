package jp.speakbuddy.edisonandroidexercise.repository.network


import jp.speakbuddy.edisonandroidexercise.model.Fact
import retrofit2.http.GET

interface FactAPIService {
    @GET("fact/")
    suspend fun getFact(): Fact
}


