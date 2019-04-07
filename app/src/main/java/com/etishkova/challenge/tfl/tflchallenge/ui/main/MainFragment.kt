package com.etishkova.challenge.tfl.tflchallenge.ui.main

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.etishkova.challenge.tfl.tflchallenge.R
import com.etishkova.challenge.tfl.tflchallenge.models.RoadStatus
import com.etishkova.challenge.tfl.tflchallenge.models.SearchRoadInfoState
import com.etishkova.challenge.tfl.tflchallenge.mosby.RoadInfoSearchPresenter
import com.etishkova.challenge.tfl.tflchallenge.mosby.RoadInfoSearchView
import com.etishkova.challenge.tfl.tflchallenge.repository.Interactor
import com.hannesdorfmann.mosby3.mvi.MviFragment
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxSearchView
import io.reactivex.Observable
import io.reactivex.Observable.create
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.subjects.PublishSubject
import org.reactivestreams.Subscriber
import java.util.concurrent.TimeUnit


class MainFragment : MviFragment<RoadInfoSearchView, RoadInfoSearchPresenter>(), RoadInfoSearchView {

    override fun createPresenter(): RoadInfoSearchPresenter {
        return RoadInfoSearchPresenter(Interactor())
    }

    @BindView(R.id.svQuerySearchView)
    lateinit var searchView: SearchView

    @BindView(R.id.rlContainer)
    lateinit var container: RelativeLayout

    @BindView(R.id.btnSearch)
    lateinit var btnSearchButton: Button

    @BindView(R.id.tvDisplayName)
    lateinit var tvRoadName: TextView

    @BindView(R.id.tvRoadStatus)
    lateinit var tvRoadStatus: TextView

    @BindView(R.id.tvRoadStatusDescription)
    lateinit var tvRoadStatusDescription: TextView

    @BindView(R.id.pbInProgress)
    lateinit var pbInProgress: ProgressBar

    @BindView(R.id.tvError)
    lateinit var tvError: TextView

    private lateinit var unbinder: Unbinder

    private val roadNameSubject: PublishSubject<String> = PublishSubject.create<String>()
    val clickEvent: Observable<String> = roadNameSubject

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        ButterKnife.setDebug(true)
        unbinder = ButterKnife.bind(this, view)

        setupLayout(view)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    //for some reason I get property not initialised exception, so initializing here
    private fun setupLayout(view: View) {
        container = view.findViewById(R.id.rlContainer)
        searchView = view.findViewById(R.id.svQuerySearchView)
        btnSearchButton = view.findViewById(R.id.btnSearch)
        tvRoadName = view.findViewById(R.id.tvDisplayName)
        tvRoadStatus = view.findViewById(R.id.tvRoadStatus)
        tvRoadStatusDescription = view.findViewById(R.id.tvRoadStatusDescription)
        pbInProgress = view.findViewById(R.id.pbInProgress)
        tvError = view.findViewById(R.id.tvError)
        btnSearchButton.setOnClickListener { searchIntent() }
    }

    override fun searchIntent(): Observable<String> {
        return RxView.clicks(btnSearchButton)
            .map { searchView.query.toString() }
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
    }

    override fun render(viewRoadInfoState: SearchRoadInfoState) {
        when (viewRoadInfoState) {
            is SearchRoadInfoState.SearchNotStartedYet -> showSearchNotStarted()
            is SearchRoadInfoState.Loading -> showLoading()
            is SearchRoadInfoState.Error -> showError(viewRoadInfoState.error)
            is SearchRoadInfoState.RequestError -> showRequestError(viewRoadInfoState.errorRoadStatus)
            is SearchRoadInfoState.SearchResult -> showResult(viewRoadInfoState.roadStatus)
        }
    }

    private fun showSearchNotStarted() {
        TransitionManager.beginDelayedTransition(container)
        pbInProgress.visibility = View.GONE
        tvRoadName.visibility = View.GONE
        tvRoadStatus.visibility = View.GONE
        tvRoadStatusDescription.visibility = View.GONE
        tvError.visibility = View.GONE
    }

    private fun showLoading() {
        TransitionManager.beginDelayedTransition(container)
        pbInProgress.visibility = View.VISIBLE
        tvRoadName.visibility = View.GONE
        tvRoadStatus.visibility = View.GONE
        tvRoadStatusDescription.visibility = View.GONE
        tvError.visibility = View.GONE
    }

    private fun showError(error: Throwable) {
        TransitionManager.beginDelayedTransition(container)
        pbInProgress.visibility = View.GONE
        tvRoadName.visibility = View.GONE
        tvRoadStatus.visibility = View.GONE
        tvRoadStatusDescription.visibility = View.GONE
        tvError.visibility = View.VISIBLE
        tvError.setText(R.string.error_text)
    }

    private fun showRequestError(errorRoadStatus: RoadStatus) {
        TransitionManager.beginDelayedTransition(container)
        pbInProgress.visibility = View.GONE
        tvRoadName.visibility = View.GONE
        tvRoadStatus.visibility = View.GONE
        tvRoadStatusDescription.visibility = View.GONE
        tvError.visibility = View.VISIBLE
        tvError.text = errorRoadStatus.exceptionType
    }

    private fun showResult(roadInfoState: RoadStatus) {
        TransitionManager.beginDelayedTransition(container)
        pbInProgress.visibility = View.GONE
        tvRoadName.visibility = View.VISIBLE
        tvRoadStatus.visibility = View.VISIBLE
        tvRoadStatusDescription.visibility = View.VISIBLE
        tvError.visibility = View.GONE
        //TODO: show values
    }
}
