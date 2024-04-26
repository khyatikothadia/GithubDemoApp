package com.example.githubdemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Module that provides dependencies for Coroutine Dispatcher.
 *
 * @see SingletonComponent
 */
@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatchersModule {

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}