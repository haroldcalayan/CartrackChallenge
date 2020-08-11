/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/11/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.signin

import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel : BaseViewModel() {

    val username = ObservableField("")
    val password = ObservableField("")

    fun onClickClose() {

    }

    fun onClickSubmit() {
        val usernameStr = username.get()
        val passwordStr = password.get()

        if (TextUtils.isEmpty(usernameStr)) {
            _errorMessage.value = R.string.app_name
            return
        }

        if (TextUtils.isEmpty(passwordStr)) {
            _errorMessage.value = R.string.app_name
            return
        }

        _loading.value = true

        viewModelScope.launch {

            _loading.value = false
        }
    }

    /*
    LiveData Getters
     */
    val loading: LiveData<Boolean>
        get() = _loading
    val loginSuccess: LiveData<Int>
        get() = _loginSuccess
    val errorMessage: LiveData<Int>
        get() = _errorMessage
}