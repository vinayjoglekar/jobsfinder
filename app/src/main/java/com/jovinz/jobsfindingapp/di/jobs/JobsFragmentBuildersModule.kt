package com.jovinz.jobsfindingapp.di.jobs

import com.jovinz.jobsfindingapp.ui.JobDetailFragment
import com.jovinz.jobsfindingapp.ui.JobsListingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class JobsFragmentBuildersModule {


    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): JobsListingFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragment(): JobDetailFragment
}