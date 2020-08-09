/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge

import android.app.Application
import timber.log.Timber

class CartrackApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initLog()
    }

    private fun initLog() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: CartrackApplication
    }
}