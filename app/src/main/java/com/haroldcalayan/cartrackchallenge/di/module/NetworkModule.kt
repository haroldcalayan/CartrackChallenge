/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.di.module

import com.haroldcalayan.cartrackchallenge.BuildConfig
import com.haroldcalayan.cartrackchallenge.data.source.remote.ApiClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(okHttpBuilder: OkHttpClient.Builder) = okHttpBuilder.build()

    @Provides
    @Singleton
    fun provideHttpClientBuilder(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        var okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)
        return okHttpBuilder
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideApiClient(client: OkHttpClient.Builder) = ApiClient(BuildConfig.BASE_APP_URL, client)

}