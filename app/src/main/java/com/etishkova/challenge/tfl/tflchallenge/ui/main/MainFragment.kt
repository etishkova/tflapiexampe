package com.etishkova.challenge.tfl.tflchallenge.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.Unbinder
import com.etishkova.challenge.tfl.tflchallenge.R
import com.etishkova.challenge.tfl.tflchallenge.models.SearchRoadInfoState
import com.etishkova.challenge.tfl.tflchallenge.mosby.RoadInfoSearchPresenter
import com.etishkova.challenge.tfl.tflchallenge.mosby.RoadInfoSearchView
import com.etishkova.challenge.tfl.tflchallenge.repository.Interactor
import com.hannesdorfmann.mosby3.mvi.MviFragment
import io.reactivex.Observable

class MainFragment : MviFragment<RoadInfoSearchView, RoadInfoSearchPresenter>(), RoadInfoSearchView {

    override fun createPresenter(): RoadInfoSearchPresenter {
        return RoadInfoSearchPresenter(Interactor())
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var unbinder: Unbinder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        ButterKnife.setDebug(true)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    override fun searchIntent(): Observable<String> {
        Toast.makeText(context, "SearchIntent", Toast.LENGTH_SHORT).show()
        return Observable.empty()
    }

    override fun render(viewRoadInfoState: SearchRoadInfoState) {
        Toast.makeText(context, "Render", Toast.LENGTH_SHORT).show()
    }

    fun showSearchNotStarted() {

    }

    fun showLoading() {

    }

    fun showResult() {

    }

    fun showEmptyResult() {

    }

    fun showError() {

    }
}
