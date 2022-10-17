package studio.stilip.proffer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import studio.stilip.proffer.data.repositories.AdRepositoryImpl
import studio.stilip.proffer.domain.repository_interface.AdRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAdRepository(repositoryImpl: AdRepositoryImpl): AdRepository
}