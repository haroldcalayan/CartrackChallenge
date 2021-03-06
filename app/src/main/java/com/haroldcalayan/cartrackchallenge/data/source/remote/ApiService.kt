/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data.source.remote

import com.haroldcalayan.cartrackchallenge.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>??
}