/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.di.component

import android.content.Context
import com.haroldcalayan.cartrackchallenge.di.module.NetworkModule
import com.haroldcalayan.cartrackchallenge.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ NetworkModule::class, RepositoryModule::class ])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}