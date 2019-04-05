package com.etishkova.challenge.tfl.tflchallenge.mosby

import com.etishkova.challenge.tfl.tflchallenge.models.SearchRoadInfoState
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface RoadInfoSearchView: MvpView {

    fun searchIntent(): Observable<String>

    fun render(viewRoadInfoState: SearchRoadInfoState)
}