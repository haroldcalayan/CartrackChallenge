/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data

import com.haroldcalayan.cartrackchallenge.CartrackApplication
import com.haroldcalayan.cartrackchallenge.data.model.User
import com.haroldcalayan.cartrackchallenge.data.source.local.CartrackRoomDatabase
import com.haroldcalayan.cartrackchallenge.data.source.remote.ApiClient
import javax.inject.Inject

class UserRepositoryImpl(private val appDatabase: CartrackRoomDatabase) : UserRepository {

    @Inject
    lateinit var api: ApiClient

    init {
        CartrackApplication.instance.appComponent.inject(this)
    }

    override suspend fun getAllUsers(): List<User> {
        var users = api.getService()?.getUsers()

        if(users!!.isNotEmpty()) {
            appDatabase.userDao().deleteAll()
            appDatabase.userDao().insertUsers(users)
        } else {
            users = appDatabase.userDao().getAllUsers()
        }

        return users
    }

    override suspend fun getSomeUsers(limit: Int, offset: Int): List<User> {
        var users = appDatabase.userDao().getSomeUsers(limit, offset)
        if(users!!.isNotEmpty()) return users

        var usersFromRemote = api.getService()?.getUsers()

        if(usersFromRemote!!.isNotEmpty()) {
            appDatabase.userDao().deleteAll()
            appDatabase.userDao().insertUsers(users)
            return appDatabase.userDao().getSomeUsers(limit, offset)
        }

        return emptyList()
    }

    override suspend fun getCachedUsers() = appDatabase.userDao().getAllUsers()

    override suspend fun getUser(id: Int): List<User> = appDatabase.userDao().getUser(id)

    override suspend fun insertUser(user: User) = appDatabase.userDao().insert(user)

    override suspend fun deleteUsers() = appDatabase.userDao().deleteAll()
}