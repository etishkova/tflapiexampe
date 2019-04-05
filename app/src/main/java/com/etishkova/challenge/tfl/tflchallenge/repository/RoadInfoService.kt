package com.etishkova.challenge.tfl.tflchallenge.repository

import com.etishkova.challenge.tfl.tflchallenge.models.RoadStatus
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RoadInfoService(
    private val interceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY),
    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build(),
    private val api: RoadInfoServiceApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RoadInfoServiceApi::class.java)
) {
    private lateinit var roadInfoServiceApi: RoadInfoServiceApi

    //TODO:replace appId and appKey
    fun fetchRoadInfo(roadName: String): Observable<RoadStatus> {
        return roadInfoServiceApi.fetchRoadInformation(roadName, "appId", "appKey")
    }

    companion object {
        const val BASE_URL = "https://api.tfl.gov.uk/"
    }
}