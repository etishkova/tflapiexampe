package com.etishkova.challenge.tfl.tflchallenge.models

sealed class SearchRoadInfoState {

    class SearchNotStartedYet : SearchRoadInfoState()

    class Loading : SearchRoadInfoState()

    class EmptyResult : SearchRoadInfoState()

    class SearchResult(val roadName: String, val roadStatus: RoadStatus) : SearchRoadInfoState()

    class HttpError(val roadName: String, val error: Throwable) : SearchRoadInfoState()

    class Error(val error: Throwable): SearchRoadInfoState()
}