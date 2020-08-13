/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldcalayan.cartrackchallenge.base.BaseViewModel
import com.haroldcalayan.cartrackchallenge.data.UserRepository
import com.haroldcalayan.cartrackchallenge.data.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapsViewModel : BaseViewModel() {

    @Inject
    lateinit var userRepository: UserRepository

    private val _users = MutableLiveData<List<User>>()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            _users.value = userRepository.getAllUsers()
        }
    }

    fun getUsers(limit: Int, offset: Int) {

    }

    fun getCachedUsers() {
        viewModelScope.launch {
            _users.value = userRepository.getCachedUsers()
        }
    }

    /*
    LiveData Getters
     */
    val users: LiveData<List<User>>
        get() = _users
}