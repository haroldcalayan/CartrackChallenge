/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val _loading = MutableLiveData<Boolean>()
    val _loginSuccess = MutableLiveData<Int>()
    val _errorMessage = MutableLiveData<Int>()
}