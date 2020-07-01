package com.jovinz.jobsfindingapp.di

import com.jovinz.jobsfindingapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    //provides activity to the AppComponent. [potential client for injection to AppComponent]
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}