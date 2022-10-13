package com.ihebchiha.tech_test.di

import com.google.gson.GsonBuilder
import com.ihebchiha.tech_test.data.JetbrainsReposApi
import com.ihebchiha.tech_test.data.repository.JetbrainsReposRepositoryImpl
import com.ihebchiha.tech_test.domain.JetbrainsReposRepository
import com.ihebchiha.tech_test.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideReposRepository(api: JetbrainsReposApi): JetbrainsReposRepository{
        return JetbrainsReposRepositoryImpl(api)
    }
}