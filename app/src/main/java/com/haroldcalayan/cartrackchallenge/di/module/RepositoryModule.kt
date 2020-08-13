/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.di.module

import com.haroldcalayan.cartrackchallenge.data.AuthenticationRepositoryImpl
import com.haroldcalayan.cartrackchallenge.data.UserRepositoryImpl
import com.haroldcalayan.cartrackchallenge.data.source.AuthenticationRepository
import com.haroldcalayan.cartrackchallenge.data.source.UserRepository
import com.haroldcalayan.cartrackchallenge.data.source.local.CartrackRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(appDatabase: CartrackRoomDatabase) : AuthenticationRepository = AuthenticationRepositoryImpl(appDatabase)

    @Provides
    @Singleton
    fun provideUserRepository(appDatabase: CartrackRoomDatabase) : UserRepository = UserRepositoryImpl(appDatabase)
}