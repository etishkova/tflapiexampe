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
                if (roadInfoResult.httpStatusCode == 200) {
                    SearchRoadInfoState.SearchResult(roadName, roadInfoResult)
                } else SearchRoadInfoState.RequestError(roadName, roadInfoResult)
            }
            .startWith(SearchRoadInfoState.Loading())
            .onErrorReturn { error -> SearchRoadInfoState.Error(roadName, error) }
    }
}