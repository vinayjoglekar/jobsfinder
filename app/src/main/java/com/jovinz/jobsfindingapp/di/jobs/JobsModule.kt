package com.jovinz.jobsfindingapp.di.jobs

import com.jovinz.jobsfindingapp.network.JobsApi
import com.jovinz.jobsfindingapp.ui.JobsRecyclerViewAdapter
import com.jovinz.jobsfindingapp.utils.VerticalSpacingItemDecoration
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class JobsModule {

    @JobsScope
    @Provides
    fun provideJobsRecyclerViewAdapter(): JobsRecyclerViewAdapter = JobsRecyclerViewAdapter()

    @JobsScope
    @Provides
    fun provideRecyclerViewItemDecoration(): VerticalSpacingItemDecoration =
        VerticalSpacingItemDecoration(12)

    @JobsScope
    @Provides
    fun providesJobsApi(retrofit: Retrofit): JobsApi = retrofit.create(JobsApi::class.java)


}