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
import com.haroldcalayan.cartrackchallenge.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * from user")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * from user WHERE id = :id")
    suspend fun getUser(id: Int): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}