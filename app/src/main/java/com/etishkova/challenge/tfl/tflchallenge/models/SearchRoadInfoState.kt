package com.etishkova.challenge.tfl.tflchallenge.models

sealed class SearchRoadInfoState {

    class SearchNotStartedYet : SearchRoadInfoState()

    class Loading : SearchRoadInfoState()

    class SearchResult(val searchRoadName: String, val roadStatus: RoadStatus) : SearchRoadInfoState()

    /**
     * This error type is used when error is returned with valid JSON value
     */
    class RequestError(val searchRoadName: String, val errorRoadStatus: RoadStatus) : SearchRoadInfoState()

    /**
     * This error type is used when error is returned without valid JSON value
     * For example, occasionally i get the following error:
     * Request Limit exceeded contact digital@tfl.gov.uk to request a higher rate limit for the api
     * calls you are using.  If you have not specified an app_id and app_key with your request you are
     * using anonymous access which is limited.  Go to the open data section of tfl.gov.uk to register for
     * free access tokens and higher rate limits.
     **/
    class Error(val searchRoadName: String, val error: Throwable) : SearchRoadInfoState()
}