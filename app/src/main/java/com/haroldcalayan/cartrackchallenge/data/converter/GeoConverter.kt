/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/13/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.haroldcalayan.cartrackchallenge.data.model.Geo

class GeoConverter {

    companion object {

        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun fromGeo(geo: Geo) = if(geo == null) "" else gson.toJson(geo)

        @TypeConverter
        @JvmStatic
        fun toGeo(string: String) = gson.fromJson(string, Geo::class.java)

    }
}