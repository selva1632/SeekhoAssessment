package com.selva.anime.di

import com.selva.anime.domain.usecase.impl.GetAnimeUseCaseImpl
import com.selva.anime.domain.usecase.interfaces.GetAnimeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GetAnimeUseCaseModule {
    @Binds
    @Singleton
    abstract fun bindGetAnimeUseCase(useCaseImpl: GetAnimeUseCaseImpl): GetAnimeUseCase
}