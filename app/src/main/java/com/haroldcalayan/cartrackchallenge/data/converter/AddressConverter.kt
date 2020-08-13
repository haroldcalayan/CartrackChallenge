/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/13/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.haroldcalayan.cartrackchallenge.data.model.Address

class AddressConverter {

    companion object {

        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun fromAddress(address: Address) = if(address == null) "" else gson.toJson(address)

        @TypeConverter
        @JvmStatic
        fun toAddress(string: String) = gson.fromJson(string, Address::class.java)

    }
}