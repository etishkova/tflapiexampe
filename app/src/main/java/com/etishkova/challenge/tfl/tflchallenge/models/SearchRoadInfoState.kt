package com.etishkova.challenge.tfl.tflchallenge.models

sealed class SearchRoadInfoState {

    class SearchNotStartedYet : SearchRoadInfoState()

    class Loading : SearchRoadInfoState()

    class EmptyResult : SearchRoadInfoState()

    class SearchResult : SearchRoadInfoState()

    class Error : SearchRoadInfoState()
}