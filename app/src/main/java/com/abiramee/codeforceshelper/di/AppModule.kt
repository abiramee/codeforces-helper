package com.abiramee.codeforceshelper.di

import android.content.Context
import com.abiramee.codeforceshelper.data.remote.dto.CodeForcesApi
import com.abiramee.codeforceshelper.common.Constants
import com.abiramee.codeforceshelper.data.repository.SolvedListRepository
import com.abiramee.codeforceshelper.domain.repository.SolvedListRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCodeforcesApi(): CodeForcesApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CodeForcesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: CodeForcesApi) : SolvedListRepository {
        return SolvedListRepositoryImp(api);
    }
}