package com.example.core.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.core.network.BuildConfig
import com.example.core.network.utils.Network
import com.pluto.plugins.network.okhttp.PlutoOkhttpInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(Network.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
//        headerInterceptor: Interceptor,
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(PlutoOkhttpInterceptor)
        }
//        httpClientBuilder.addInterceptor(headerInterceptor)

        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

//    @Provides
//    @Singleton
//    fun provideHeaderInterceptor(environmentConfig: EnvironmentConfig): Interceptor =
//        Interceptor { chain ->
//            val originalRequest = chain.request()
//            val url: HttpUrl = originalRequest.url.newBuilder()
//                .addQueryParameter(Network.Queries.API_KEY, environmentConfig.getApiKey())
//                .build()
//            val newRequest = originalRequest.newBuilder().url(url).build()
//            chain.proceed(newRequest)
//        }
}
