/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.haroldcalayan.cartrackchallenge.data.converter.AddressConverter
import com.haroldcalayan.cartrackchallenge.data.converter.CompanyConverter
import com.haroldcalayan.cartrackchallenge.data.converter.GeoConverter
import com.haroldcalayan.cartrackchallenge.data.model.Account
import com.haroldcalayan.cartrackchallenge.data.model.User

@Database(entities = [Account::class, User::class], version = 1, exportSchema = false)
@TypeConverters(AddressConverter::class, CompanyConverter::class, GeoConverter::class)
abstract class CartrackRoomDatabase : RoomDatabase() {

    abstract fun accountDao() : AccountDao
    abstract fun userDao() : UserDao

}