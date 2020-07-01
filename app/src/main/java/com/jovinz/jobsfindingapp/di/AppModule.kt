package com.jovinz.jobsfindingapp.di

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jovinz.jobsfindingapp.BaseApplication
import dagger.Module
import dagger.Provides
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
}