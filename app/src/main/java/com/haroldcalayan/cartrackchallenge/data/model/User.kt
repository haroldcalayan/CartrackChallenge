/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey @ColumnInfo(name = "id") private val id: Int,
    private val name: String,
    private val username: String,
    private val email: String,
    private val address: Address,
    private val phone: String,
    private val website: String,
    private val company: Company
)