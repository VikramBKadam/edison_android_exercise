package jp.speakbuddy.edisonandroidexercise.repository

import jp.speakbuddy.edisonandroidexercise.model.FactResponse
import jp.speakbuddy.edisonandroidexercise.repository.network.FactAPIService
import javax.inject.Inject

class APIRepository @Inject constructor(private val apiService: FactAPIService) {
    suspend fun getFact(): FactResponse {
        return try {
            val response = apiService.getFact()
            FactResponse.Success(response)
        } catch (e: Exception) {
            FactResponse.Error(e.localizedMessage ?: "something went wrong")
        }
    }
}
