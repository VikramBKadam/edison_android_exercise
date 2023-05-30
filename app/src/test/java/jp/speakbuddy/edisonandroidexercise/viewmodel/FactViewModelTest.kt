package jp.speakbuddy.edisonandroidexercise.viewmodel

import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TEST_FACT_LENGTH_MORE_THAN_100
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TEST_FACT_WITH_CAT
import jp.speakbuddy.edisonandroidexercise.model.Fact
import jp.speakbuddy.edisonandroidexercise.model.FactResponse
import jp.speakbuddy.edisonandroidexercise.repository.APIRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class FactViewModelTest {
    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    private lateinit var factViewModel: FactViewModel

    @Mock
    lateinit var apiRepository: APIRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        factViewModel = FactViewModel(apiRepository, testDispatcher, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @org.junit.Test
    fun `updateFact should call save fact of apiRepository is call`() {
        runTest {
            val testFact = Fact(TEST_FACT_WITH_CAT, TEST_FACT_LENGTH_MORE_THAN_100)
            val factResponse = FactResponse.Success(testFact)
            whenever(apiRepository.getFact()).thenReturn(
                factResponse
            )
            factViewModel.updateFact()
            verify(apiRepository).saveFact(testFact)

        }
    }

    @org.junit.Test
    fun `getFactFromLocal should update factState`() {
        runTest {
            val testFact = Fact(TEST_FACT_WITH_CAT, TEST_FACT_LENGTH_MORE_THAN_100)
            whenever(apiRepository.getFactFromLocal()).thenReturn(
                testFact
            )
            factViewModel.getFactFromLocal()
            assertEquals(testFact, factViewModel.factState.value)
        }
    }
}
