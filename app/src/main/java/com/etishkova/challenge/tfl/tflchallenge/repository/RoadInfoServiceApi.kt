package com.etishkova.challenge.tfl.tflchallenge.repository

import com.etishkova.challenge.tfl.tflchallenge.models.RoadStatus
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RoadInfoServiceApi {

    @GET("/road/{roadName}")
    fun fetchRoadInformation(@Path("roadName") roadName: String,
                             @Query("appId") appId: String,
                             @Query("appKey") appKey: String): Observable<RoadStatus>
}