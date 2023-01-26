package studio.stilip.proffer.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import studio.stilip.proffer.BuildConfig
import studio.stilip.proffer.data.api.RetrofitProvider
import studio.stilip.proffer.data.api.RetrofitServiceAd
import studio.stilip.proffer.data.api.RetrofitServiceUser
import studio.stilip.proffer.data.dao.UserDBDao
import studio.stilip.proffer.data.database.UserDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideRetrofitServiceAd(retrofitProvider: RetrofitProvider): RetrofitServiceAd =
        retrofitProvider.retrofitServiceAd

    @Provides
    fun provideRetrofitServiceUser(retrofitProvider: RetrofitProvider): RetrofitServiceUser =
        retrofitProvider.retrofitServiceUser

    @Singleton
    @Provides
    fun provideUserDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        UserDatabase::class.java,
        BuildConfig.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun provideUserDBDao(db: UserDatabase): UserDBDao = db.userDBDao()

}