package com.ihebchiha.tech_test.di

import android.content.Context
import com.ihebchiha.tech_test.BuildConfig
import com.ihebchiha.tech_test.data.JetbrainsReposApi
import com.ihebchiha.tech_test.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JetbrainsApiClientModule {

    private const val CACHE_SIZE = 10 * 1024 * 1024 // 10 MB
    private const val CONNECT_TIMEOUT = "10"
    private const val WRITE_TIMEOUT = "60"
    private const val READ_TIMEOUT = "30"


    @Provides
    @Singleton
    fun providesRetrofitInstance(okHttpClient: OkHttpClient): JetbrainsReposApi =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JetbrainsReposApi::class.java)

    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(httpInterceptor())
        }
        return client.build()

    }

    @Provides
    @Singleton
    fun providesOkHttpCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE.toLong())
    }

    private fun httpInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }



}
