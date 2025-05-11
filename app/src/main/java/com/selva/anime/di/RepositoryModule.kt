package com.selva.anime.di

import com.selva.anime.repository.impl.AnimeRepositoryImpl
import com.selva.anime.repository.interfaces.AnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAnimeRepository(repository: AnimeRepositoryImpl): AnimeRepository
}