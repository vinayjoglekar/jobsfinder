package com.jovinz.jobsfindingapp.di.jobs

import com.jovinz.jobsfindingapp.ui.fragments.CategorySelectionFragment
import com.jovinz.jobsfindingapp.ui.fragments.JobDetailFragment
import com.jovinz.jobsfindingapp.ui.fragments.JobsListingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class JobsFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): JobsListingFragment

    @ContributesAndroidInjector
    abstract fun contributeJobDetailFragment(): JobDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeCategoriesSelectionFragment(): CategorySelectionFragment
}