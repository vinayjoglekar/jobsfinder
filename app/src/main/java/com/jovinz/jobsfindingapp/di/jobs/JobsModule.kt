package com.jovinz.jobsfindingapp.di.jobs

import com.jovinz.jobsfindingapp.network.JobsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class JobsModule {

    @Provides
    fun providesJobsApi(retrofit: Retrofit): JobsApi {
        return retrofit.create(JobsApi::class.java)
    }
}