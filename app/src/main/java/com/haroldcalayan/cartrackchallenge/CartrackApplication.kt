/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge

import android.app.Application
import com.haroldcalayan.cartrackchallenge.di.DaggerAppComponent
import com.haroldcalayan.cartrackchallenge.di.component.AppComponent
import timber.log.Timber

class CartrackApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initLog()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    private fun initLog() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: CartrackApplication
    }
}