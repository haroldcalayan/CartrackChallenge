/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data

import com.haroldcalayan.cartrackchallenge.data.model.User
import com.haroldcalayan.cartrackchallenge.data.source.local.CartrackRoomDatabase

class UserRepositoryImpl(private val appDatabase: CartrackRoomDatabase) : UserRepository {

    override suspend fun getAllUsers(): List<User> = appDatabase.userDao().getAllUsers()

    override suspend fun getUser(id: Int): List<User> = appDatabase.userDao().getUser(id)

    override suspend fun insertUser(user: User) = appDatabase.userDao().insert(user)

    override suspend fun deleteUsers() = appDatabase.userDao().deleteAll()
}