/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.splash

import android.content.Intent
import android.view.View
import com.haroldcalayan.cartrackchallenge.BR
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseActivity
import com.haroldcalayan.cartrackchallenge.databinding.ActivitySplashBinding
import com.haroldcalayan.cartrackchallenge.ui.login.LoginActivity
import com.haroldcalayan.cartrackchallenge.ui.maps.MapsActivity
import kotlinx.coroutines.*

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun getLayout() = R.layout.activity_splash

    override fun getBindingVariable() = BR.viewModel

    override fun initData() {}

    override fun initViews() {
        hideSystemUI()
        startTimer()
    }

    override fun subscribe() {}

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    private fun startTimer() {
        activityScope.launch {
            delay(3000)
            startMain()
            finish()
        }
    }

    private fun startMain() {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE)
    }
}