package com.jovinz.jobsfindingapp.di.common

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jovinz.jobsfindingapp.BaseApplication
import com.jovinz.jobsfindingapp.utils.Constants.BASE_URL
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
    fun provideGlideInstance(
        application: BaseApplication?
    ): RequestManager {
        return Glide.with(application?.applicationContext!!)
    }

    @Singleton
    @Provides
    fun provideAppDrawable(application: BaseApplication?): Drawable {
        return ContextCompat.getDrawable(
            application!!,
            com.jovinz.jobsfindingapp.R.mipmap.ic_launcher_round
        )!!
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {

        val logger = HttpLoggingInterceptor(    )
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logger).build()
        return Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    }
}