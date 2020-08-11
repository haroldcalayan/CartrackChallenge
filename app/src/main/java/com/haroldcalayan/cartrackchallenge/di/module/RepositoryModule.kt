/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.di.module

import com.haroldcalayan.cartrackchallenge.data.AuthenticationRepository
import com.haroldcalayan.cartrackchallenge.data.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository() = AuthenticationRepository()

    @Provides
    @Singleton
    fun provideUserRepository() = UserRepository()
}