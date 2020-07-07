package com.jovinz.jobsfindingapp.app

import android.content.Context
import com.jovinz.jobsfindingapp.di.common.AppModule
import com.jovinz.jobsfindingapp.di.common.DaggerAppComponent
import com.jovinz.jobsfindingapp.di.jobs.JobsModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    init {
        instance = this
    }

    companion object {
        private var instance: BaseApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication?>? {
        return DaggerAppComponent.builder().application(this)?.appModule(AppModule())?.build()
    }
}