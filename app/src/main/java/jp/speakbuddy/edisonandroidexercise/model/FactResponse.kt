package jp.speakbuddy.edisonandroidexercise.model

sealed class FactResponse {
    data class Success(val factResponse: Fact) : FactResponse()
    data class Error(val error: String) : FactResponse()
    class Loading<T> : FactResponse()
}