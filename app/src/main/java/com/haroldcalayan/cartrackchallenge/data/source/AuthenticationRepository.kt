/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/13/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data.source

import com.haroldcalayan.cartrackchallenge.data.model.Account

interface AuthenticationRepository {

    suspend fun getAllAccounts(): List<Account>

    suspend fun getAccount(username: String): Account

    suspend fun insertAccount(account: Account)

    suspend fun deleteAccounts()

}