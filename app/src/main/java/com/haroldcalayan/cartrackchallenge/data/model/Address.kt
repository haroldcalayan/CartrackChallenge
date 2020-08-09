/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data.model

data class Address (
    private val street: String,
    private val suite: String,
    private val city: String,
    private val zipcode: String,
    private val geo: Geo
)