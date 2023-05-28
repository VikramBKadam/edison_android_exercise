package jp.speakbuddy.edisonandroidexercise.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.di.DefaultDispatcher
import jp.speakbuddy.edisonandroidexercise.di.IODispatcher
import jp.speakbuddy.edisonandroidexercise.model.FactResponse
import jp.speakbuddy.edisonandroidexercise.repository.APIRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val apiRepository: APIRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    fun updateFact(completion: () -> Unit): String =
        runBlocking {
            when (val response = apiRepository.getFact()) {
                is FactResponse.Success -> {
                    response.factResponse.fact
                }
                is FactResponse.Error -> {
                    "something went wrong. error = ${response.error}"
                }
                else -> {
                    "loading"
                }
            }
        }

    companion object {
        private val TAG = FactViewModel.Companion::class.java

    }
}
