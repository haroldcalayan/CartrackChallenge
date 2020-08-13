/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/13/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.di.module

import android.content.Context
import androidx.room.Room
import com.haroldcalayan.cartrackchallenge.CartrackApplication
import com.haroldcalayan.cartrackchallenge.data.source.local.CartrackRoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class AppModule(private val app: CartrackApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): CartrackRoomDatabase {
        return Room.databaseBuilder(context, CartrackRoomDatabase::class.java,
            "cartrack_database").build()
    }
}