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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseViewModel
import com.haroldcalayan.cartrackchallenge.data.AuthenticationRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SignInViewModel : BaseViewModel() {

    @Inject
    lateinit var authenticationRepository: AuthenticationRepository

    val username = ObservableField("")
    val password = ObservableField("")

    private val _close = MutableLiveData<Unit>()
    private val _loginSuccess = MutableLiveData<Unit>()

    fun onClickClose() {
        _close.value = Unit
    }

    fun onClickSubmit() {
        val usernameStr = username.get()
        val passwordStr = password.get()

        if (TextUtils.isEmpty(usernameStr)) {
            _errorMessage.value = R.string.signin_error_username_must_not_empty
            return
        }

        if (TextUtils.isEmpty(passwordStr)) {
            _errorMessage.value = R.string.signin_error_password_must_not_empty
            return
        }

        if (usernameStr!!.length < MINIMUM_USERNAME_LENGTH) {
            _errorMessage.value = R.string.signin_error_username_must_3_characters
            return
        }

        if (passwordStr!!.length < MINIMUM_PASSWORD_LENGTH) {
            _errorMessage.value = R.string.signin_error_password_must_8_characters
            return
        }

        _loading.value = true
        viewModelScope.launch {
            val account = authenticationRepository.getAccount(usernameStr)
            Timber.d("account: $account")
            if(account == null) {
                _errorMessage.value = R.string.signin_error_user_is_not_registered
            } else if(account.password != passwordStr) {
                _errorMessage.value = R.string.signin_error_password_does_not_match
            } else {
                _loginSuccess.value = Unit
            }

            _loading.value = false
        }
    }

    /*
    LiveData Getters
     */
    val close: LiveData<Unit>
        get() = _close
    val loginSuccess: LiveData<Unit>
        get() = _loginSuccess

    companion object {
        const val MINIMUM_USERNAME_LENGTH = 3
        const val MINIMUM_PASSWORD_LENGTH = 8
    }
}