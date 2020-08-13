/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.di.component

import com.haroldcalayan.cartrackchallenge.data.UserRepositoryImpl
import com.haroldcalayan.cartrackchallenge.di.module.AppModule
import com.haroldcalayan.cartrackchallenge.di.module.NetworkModule
import com.haroldcalayan.cartrackchallenge.di.module.RepositoryModule
import com.haroldcalayan.cartrackchallenge.ui.maps.MapsViewModel
import com.haroldcalayan.cartrackchallenge.ui.signin.SignInViewModel
import com.haroldcalayan.cartrackchallenge.ui.splash.SplashViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ AppModule::class, NetworkModule::class, RepositoryModule::class ])
interface AppComponent {

    // Classes that can be injected by this Component
    fun inject(viewModel: SplashViewModel)
    fun inject(viewModel: SignInViewModel)
    fun inject(viewModel: MapsViewModel)

    fun inject(repository: UserRepositoryImpl)
}