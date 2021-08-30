package com.example.gitrank.di

import com.mikail.mymovie.api.ApiHelper
import com.example.gitrank.remote.ApiHelperImpl
import com.example.gitrank.remote.GitRankApiService
import com.example.gitrank.utils.Constants.GITHUB_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper


    @Provides
    @Singleton
    fun provideRetrofit(): GitRankApiService {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS) //Backend is really slow
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GITHUB_API_BASE_URL)
            .client(client)
            .build()
            .create(GitRankApiService::class.java)
    }




}