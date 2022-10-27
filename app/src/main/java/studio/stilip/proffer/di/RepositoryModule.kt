package studio.stilip.proffer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import studio.stilip.proffer.data.repositories.AdRepositoryImpl
import studio.stilip.proffer.data.repositories.ProfileRepositoryImpl
import studio.stilip.proffer.data.repositories.SellerRepositoryImpl
import studio.stilip.proffer.domain.repository_interface.AdRepository
import studio.stilip.proffer.domain.repository_interface.ProfileRepository
import studio.stilip.proffer.domain.repository_interface.SellerRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAdRepository(repositoryImpl: AdRepositoryImpl): AdRepository

    @Binds
    abstract fun provideSellerRepository(repositoryImpl: SellerRepositoryImpl): SellerRepository

    @Binds
    abstract fun provideProfileRepository(repositoryImpl: ProfileRepositoryImpl): ProfileRepository
}