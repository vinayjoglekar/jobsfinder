package com.jovinz.jobsfindingapp.di.jobs

import com.jovinz.jobsfindingapp.network.JobsApi
import com.jovinz.jobsfindingapp.ui.JobsRecyclerViewAdapter
import com.jovinz.jobsfindingapp.utils.VerticalSpacingItemDecoration
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class JobsModule {

    @Provides
    fun provideJobsRecyclerViewAdapter(): JobsRecyclerViewAdapter = JobsRecyclerViewAdapter()

    @Provides
    fun provideRecyclerViewItemDecoration(): VerticalSpacingItemDecoration =
        VerticalSpacingItemDecoration(15)

    @Provides
    fun providesJobsApi(retrofit: Retrofit): JobsApi = retrofit.create(JobsApi::class.java)


}