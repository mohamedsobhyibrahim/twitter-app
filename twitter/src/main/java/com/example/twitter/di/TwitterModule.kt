package com.example.twitter.di

import com.example.twitter.data.datasource.remote.TwitterAPI
import com.example.twitter.data.repositories.TweetRepositoryImpl
import com.example.twitter.domain.repositories.TweetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class TwitterModule {

    @Provides
    @Singleton
    fun provideTwitterAPI(
        retrofit: Retrofit,
    ): TwitterAPI = retrofit.create()

    @Provides
    @Singleton
    fun provideTweetRepository(
        twitterAPI: TwitterAPI,
    ): TweetRepository = TweetRepositoryImpl(twitterAPI = twitterAPI)

}


