package com.selva.anime.di

import android.content.Context
import com.selva.anime.data.remote.service.AnimeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(@ApplicationContext context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AnimeService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimeService(retrofit: Retrofit): AnimeService {
        return retrofit.create(AnimeService::class.java)
    }
}