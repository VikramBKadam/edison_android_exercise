package jp.speakbuddy.edisonandroidexercise.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.di.DefaultDispatcher
import jp.speakbuddy.edisonandroidexercise.di.IODispatcher
import jp.speakbuddy.edisonandroidexercise.model.Fact
import jp.speakbuddy.edisonandroidexercise.model.FactResponse
import jp.speakbuddy.edisonandroidexercise.repository.APIRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val apiRepository: APIRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val TAG = FactViewModel::class.java.simpleName
    private val _factState: MutableStateFlow<Fact?> = MutableStateFlow(Fact("", 0))
    val factState: StateFlow<Fact?> = _factState

    suspend fun updateFact() {
        withContext(ioDispatcher) {
            when (val response = apiRepository.getFact()) {
                is FactResponse.Success -> {
                    saveFactInLocal(response.factResponse)
                }
                is FactResponse.Error -> {
                    // Handle the error appropriately
                }
                else -> {
                    // Handle other states, such as loading
                }
            }
        }
    }

    suspend fun getFactFromLocal() {
        withContext(ioDispatcher) {
            val fact = apiRepository.getFactFromLocal()
            Log.d(TAG, "getFactFromLocal fact is  $fact")
            _factState.value = fact
        }
    }

    private suspend fun saveFactInLocal(fact: Fact) {
        withContext(ioDispatcher) {
            Log.d(TAG, "saveFactInLocal fact is  $fact")
            apiRepository.saveFact(fact)
        }
    }
}

