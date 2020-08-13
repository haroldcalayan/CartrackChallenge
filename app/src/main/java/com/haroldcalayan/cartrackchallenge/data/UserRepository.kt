/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/14/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data

import com.haroldcalayan.cartrackchallenge.data.model.User

interface UserRepository {

    suspend fun getAllUsers(): List<User>

    suspend fun getSomeUsers(limit: Int, offset: Int): List<User>

    suspend fun getUser(id: Int): List<User>

    suspend fun insertUser(user: User)

    suspend fun deleteUsers()
}