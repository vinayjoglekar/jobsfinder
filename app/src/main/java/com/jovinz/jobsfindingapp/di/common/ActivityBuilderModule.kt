package com.jovinz.jobsfindingapp.di.common

import com.jovinz.jobsfindingapp.di.jobs.JobsModule
import com.jovinz.jobsfindingapp.di.jobs.JobsViewModelsModule
import com.jovinz.jobsfindingapp.network.JobsApi
import com.jovinz.jobsfindingapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    //provides activity to the AppComponent. [potential client for injection to AppComponent]
    @ContributesAndroidInjector(
        modules = [JobsViewModelsModule::class, JobsModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity

}