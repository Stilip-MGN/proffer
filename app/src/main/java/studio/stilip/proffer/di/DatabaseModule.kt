package studio.stilip.proffer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import studio.stilip.proffer.data.api.RetrofitProvider
import studio.stilip.proffer.data.api.RetrofitServiceAd

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideRetrofitServiceAd(retrofitProvider: RetrofitProvider): RetrofitServiceAd =
        retrofitProvider.retrofitServiceAd

}