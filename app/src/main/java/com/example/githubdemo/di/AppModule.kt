package com.example.githubdemo.di


import com.example.githubdemo.BuildConfig
import com.example.githubdemo.data.UserInfoDataSource
import com.example.githubdemo.data.remote.ApiService
import com.example.githubdemo.data.remote.UserInfoRemoteDataSource
import com.example.githubdemo.data.repository.UserInfoRepository
import com.example.githubdemo.util.AppConstants.TIME_OUT
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module that provides dependencies for the API, data source and repository.
 *
 * @see SingletonComponent
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }

        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }

        httpClient.apply {
            readTimeout(TIME_OUT, TimeUnit.SECONDS)
        }

        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideConverter(): Converter.Factory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient, converter: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(converter)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesUserInfoRemoteDataSource(apiService: ApiService): UserInfoDataSource {
        return UserInfoRemoteDataSource(apiService)
    }

    @Singleton
    @Provides
    fun providesUserInfoRepository(
        userInfoDataSource: UserInfoDataSource,
        dispatcher: CoroutineDispatcher
    ): UserInfoRepository {
        return UserInfoRepository(userInfoDataSource, dispatcher)
    }
}