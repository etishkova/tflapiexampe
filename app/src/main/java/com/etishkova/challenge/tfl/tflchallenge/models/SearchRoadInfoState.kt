package com.etishkova.challenge.tfl.tflchallenge.models

sealed class SearchRoadInfoState {

    class SearchNotStartedYet : SearchRoadInfoState()

    class Loading : SearchRoadInfoState()

    class EmptyResult(val searchRoadName: String) : SearchRoadInfoState()

    class SearchResult(val searchRoadName: String, val roadStatus: RoadStatus) : SearchRoadInfoState()

    class Error(val searchRoadName: String, val error: Throwable) : SearchRoadInfoState()
}