/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/14/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haroldcalayan.cartrackchallenge.base.BaseViewModel

class DetailsViewModel : BaseViewModel() {

    private val _fab = MutableLiveData<Unit>()

    fun onFabClose() {
        _fab.value = Unit
    }

    /*
    LiveData Getters
     */
    val fab: LiveData<Unit>
        get() = _fab
}