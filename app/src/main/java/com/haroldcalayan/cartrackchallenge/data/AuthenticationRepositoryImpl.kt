/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data

import com.haroldcalayan.cartrackchallenge.data.model.Account
import com.haroldcalayan.cartrackchallenge.data.source.AuthenticationRepository
import com.haroldcalayan.cartrackchallenge.data.source.local.CartrackRoomDatabase

class AuthenticationRepositoryImpl(private val appDatabase: CartrackRoomDatabase) : AuthenticationRepository {

    override suspend fun getAllAccounts(): List<Account> = appDatabase.accountDao().getAllAccounts()

    override suspend fun getAccount(username: String): Account = appDatabase.accountDao().getAccount(username)

    override suspend fun insertAccount(account: Account) = appDatabase.accountDao().insert(account)

    override suspend fun deleteAccounts() = appDatabase.accountDao().deleteAll()
}