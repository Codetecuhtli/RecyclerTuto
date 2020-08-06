package com.codetecuhtli.recyclertuto.core.di

import com.codetecuhtli.recyclertuto.network.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesMovieApi() = MovieApi.instance

}