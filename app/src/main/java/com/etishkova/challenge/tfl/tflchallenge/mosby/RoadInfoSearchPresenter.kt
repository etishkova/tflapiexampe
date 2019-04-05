package com.etishkova.challenge.tfl.tflchallenge.mosby

import com.etishkova.challenge.tfl.tflchallenge.models.SearchRoadInfoState
import com.etishkova.challenge.tfl.tflchallenge.repository.Interactor
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers

class RoadInfoSearchPresenter(
    private val interactor: Interactor
) : MviBasePresenter<RoadInfoSearchView, SearchRoadInfoState>() {

    override fun bindIntents() {

        val search = intent(RoadInfoSearchView::searchIntent)
            .switchMap(interactor::searchRoadInfo)
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(search, RoadInfoSearchView::render)
    }
}