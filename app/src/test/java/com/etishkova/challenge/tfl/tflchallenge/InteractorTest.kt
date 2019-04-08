package com.etishkova.challenge.tfl.tflchallenge

import com.etishkova.challenge.tfl.tflchallenge.models.SearchRoadInfoState
import com.etishkova.challenge.tfl.tflchallenge.repository.Interactor
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class InteractorTest {

    private lateinit var interactor: Interactor

    @Before
    fun setup(){
        interactor = Interactor()
    }

    @Test
    fun `Given empty road name is passed when search road info is called then no errors and one event is returned`() {
        val emptyRoadName = ""
        val testResult = interactor.searchRoadInfo(emptyRoadName)
        val testObserver = TestObserver<SearchRoadInfoState>()
        testResult.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `Given blank road name is passed when search road info is called then no errors and one event is returned`() {
        val blankRoadName = "   "
        val testResult = interactor.searchRoadInfo(blankRoadName)
        val testObserver = TestObserver<SearchRoadInfoState>()
        testResult.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }
}
