package jp.speakbuddy.edisonandroidexercise.model

import kotlinx.serialization.Serializable

@Serializable
data class Fact(
    val fact: String,
    val length: Int
)