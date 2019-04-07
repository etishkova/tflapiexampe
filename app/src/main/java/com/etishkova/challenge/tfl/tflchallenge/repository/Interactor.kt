package com.etishkova.challenge.tfl.tflchallenge.repository

import com.etishkova.challenge.tfl.tflchallenge.models.SearchRoadInfoState
import io.reactivex.Observable

class Interactor {

    private val roadInfoService: RoadInfoService = RoadInfoService()

    fun searchRoadInfo(roadName: String): Observable<SearchRoadInfoState> {
        if (roadName.isEmpty())
            return Observable.just(SearchRoadInfoState.SearchNotStartedYet())

        return roadInfoService.fetchRoadInfo(roadName)
            .map<SearchRoadInfoState> { roadInfoResult ->
                if (roadInfoResult.isEmpty()) {
                    SearchRoadInfoState.EmptyResult(roadName)
                } else SearchRoadInfoState.SearchResult(roadName, roadInfoResult[0])
            }
            .startWith(SearchRoadInfoState.Loading())
            .onErrorReturn { error ->
                SearchRoadInfoState.Error(roadName, error)
            }
    }
}