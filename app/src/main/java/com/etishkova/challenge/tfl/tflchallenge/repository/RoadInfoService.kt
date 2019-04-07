package com.etishkova.challenge.tfl.tflchallenge.repository

import com.etishkova.challenge.tfl.tflchallenge.BuildConfig
import com.etishkova.challenge.tfl.tflchallenge.models.RoadStatus
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

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
    private val tflAppId: String = BuildConfig.TFL_APP_ID
    private val tflKey: String = BuildConfig.TFL_KEY

    fun fetchRoadInfo(roadName: String): Observable<ArrayList<RoadStatus>> {
        return api.fetchRoadInformation(roadName, tflAppId, tflKey)
    }

    companion object {
        const val BASE_URL = "https://api.tfl.gov.uk/"
    }
}