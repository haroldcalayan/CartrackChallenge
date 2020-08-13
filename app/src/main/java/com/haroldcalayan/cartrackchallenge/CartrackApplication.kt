/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge

import android.app.Application
import com.haroldcalayan.cartrackchallenge.di.component.AppComponent
import com.haroldcalayan.cartrackchallenge.di.component.DaggerAppComponent
import com.haroldcalayan.cartrackchallenge.di.module.AppModule
import com.haroldcalayan.cartrackchallenge.di.module.RepositoryModule
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
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .repositoryModule(RepositoryModule())
            .build()
    }

    private fun initLog() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: CartrackApplication
    }
}