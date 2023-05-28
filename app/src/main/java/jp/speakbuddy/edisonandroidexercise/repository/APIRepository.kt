package jp.speakbuddy.edisonandroidexercise.repository

import jp.speakbuddy.edisonandroidexercise.network.FactAPIService
import javax.inject.Inject

class APIRepository @Inject constructor(private val apiService: FactAPIService) {
    suspend fun getFact() = apiService.getFact()
}