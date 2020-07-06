package com.jovinz.jobsfindingapp.di.common

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jovinz.jobsfindingapp.utils.Constants.BASE_URL
import com.jovinz.jobsfindingapp.utils.FlowCallAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//Application level dependencies for app : retrofit/glide which does not change for entire lifetime
//of the application

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(logger).build()
        return Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapter.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    }
}