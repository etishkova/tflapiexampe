package com.etishkova.challenge.tfl.tflchallenge.repository

import com.etishkova.challenge.tfl.tflchallenge.models.SearchRoadInfoState
import io.reactivex.Observable

class Interactor {

    private val roadInfoService: RoadInfoService = RoadInfoService()

    fun searchRoadInfo(roadName: String): Observable<SearchRoadInfoState> {
        //if (roadName.isEmpty())
        return Observable.just(SearchRoadInfoState.SearchNotStartedYet())
    }
}