/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.splash

import androidx.lifecycle.viewModelScope
import com.haroldcalayan.cartrackchallenge.base.BaseViewModel
import com.haroldcalayan.cartrackchallenge.data.AuthenticationRepository
import com.haroldcalayan.cartrackchallenge.data.model.Account
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel : BaseViewModel() {

    @Inject
    lateinit var authenticationRepository: AuthenticationRepository

    init {
        viewModelScope.launch {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
        val accounts = authenticationRepository.getAllAccounts()

        if(accounts.isEmpty()) {
            //delete data
            authenticationRepository.deleteAccounts()

            //populate data
            authenticationRepository.insertAccount(Account("user1", "Password"))
            authenticationRepository.insertAccount(Account("user2", "Password"))
        }
    }
}