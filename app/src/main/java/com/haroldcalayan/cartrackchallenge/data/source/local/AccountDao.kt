/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.haroldcalayan.cartrackchallenge.data.model.Account

@Dao
interface AccountDao {

    @Query("SELECT * FROM account")
    suspend fun getAllAccounts(): List<Account>

    @Query("SELECT * FROM account WHERE username = :username")
    suspend fun getAccount(username: String): Account

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: Account)

    @Query("DELETE FROM account")
    suspend fun deleteAll()
}