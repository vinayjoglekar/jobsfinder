package com.jovinz.jobsfindingapp.ui

import androidx.test.platform.app.InstrumentationRegistry
import com.jovinz.jobsfindingapp.app.BaseApplication
import com.jovinz.jobsfindingapp.di.common.AppComponent
import com.jovinz.jobsfindingapp.di.common.AppModule
import com.jovinz.jobsfindingapp.di.jobs.JobsModule
import it.cosenonjaviste.daggermock.DaggerMockRule

class EspressoDaggerMockRule :
    DaggerMockRule<AppComponent>(AppComponent::class.java, AppModule()) {
    companion object {
        val app: BaseApplication
            get() = InstrumentationRegistry.getInstrumentation()
                .targetContext.applicationContext as BaseApplication
    }

    init {
        customizeBuilder<AppComponent.Builder> { builder -> builder!!.application(app) }
        set { component -> component?.inject(app) }
    }
}