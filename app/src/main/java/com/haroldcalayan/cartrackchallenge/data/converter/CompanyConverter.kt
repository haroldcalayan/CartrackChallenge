/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/13/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.haroldcalayan.cartrackchallenge.data.model.Company

class CompanyConverter {

    companion object {

        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun fromCompany(company: Company) = if(company == null) "" else gson.toJson(company)

        @TypeConverter
        @JvmStatic
        fun toCompany(string: String) = gson.fromJson(string, Company::class.java)

    }
}