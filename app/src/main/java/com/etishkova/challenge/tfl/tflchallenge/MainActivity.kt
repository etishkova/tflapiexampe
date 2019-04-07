package com.etishkova.challenge.tfl.tflchallenge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.etishkova.challenge.tfl.tflchallenge.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var unbinder: Unbinder

    @BindView(R.id.flContainer)
    lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        ButterKnife.setDebug(true)
        unbinder = ButterKnife.bind(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.flContainer, MainFragment())
                .commitNow()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
    }
}
