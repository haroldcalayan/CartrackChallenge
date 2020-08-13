/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.splash

import android.content.Intent
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseActivity
import com.haroldcalayan.cartrackchallenge.databinding.ActivitySplashBinding
import com.haroldcalayan.cartrackchallenge.ui.startupmenu.StartUpMenuActivity
import kotlinx.coroutines.*

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun getLayout() = R.layout.activity_splash

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    override fun initViews() {
        super.initViews()
        hideSystemUI()
        startTimer()
    }

    private fun startTimer() {
        activityScope.launch {
            delay(SPLASH_SCREEN_LIFE)
            startMain()
            finish()
        }
    }

    private fun startMain() {
        Intent(this, StartUpMenuActivity::class.java).apply {
            startActivity(this)
        }
    }

    companion object {
        private const val SPLASH_SCREEN_LIFE = 3000L
    }
}