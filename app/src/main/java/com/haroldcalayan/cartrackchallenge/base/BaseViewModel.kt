/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haroldcalayan.cartrackchallenge.CartrackApplication
import com.haroldcalayan.cartrackchallenge.ui.signin.SignInViewModel
import com.haroldcalayan.cartrackchallenge.ui.splash.SplashViewModel

open class BaseViewModel : ViewModel() {

    protected val _loading = MutableLiveData<Boolean>()
    protected val _errorMessage = MutableLiveData<Int>()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is SplashViewModel -> CartrackApplication.instance.appComponent.inject(this)
            is SignInViewModel -> CartrackApplication.instance.appComponent.inject(this)
        }
    }

    /*
    LiveData Getters
     */
    val loading: LiveData<Boolean>
        get() = _loading
    val errorMessage: LiveData<Int>
        get() = _errorMessage
}