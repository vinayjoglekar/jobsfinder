package com.jovinz.jobsfindingapp.di.jobs

import com.google.gson.Gson
import com.jovinz.jobsfindingapp.app.BaseApplication
import com.jovinz.jobsfindingapp.data.jobs.CategoriesResponse
import com.jovinz.jobsfindingapp.data.jobs.JobsRepository
import com.jovinz.jobsfindingapp.network.JobsApi
import com.jovinz.jobsfindingapp.ui.adapter.CategoriesRecyclerViewAdapter
import com.jovinz.jobsfindingapp.ui.adapter.JobsRecyclerViewAdapter
import com.jovinz.jobsfindingapp.utils.VerticalSpacingItemDecoration
import com.jovinz.jobsfindingapp.utils.getDataFromAssetFile
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class JobsModule {

    @JobsScope
    @Provides
    fun provideJobsRecyclerViewAdapter(): JobsRecyclerViewAdapter =
        JobsRecyclerViewAdapter()

    @JobsScope
    @Provides
    fun provideRecyclerViewItemDecoration(): VerticalSpacingItemDecoration =
        VerticalSpacingItemDecoration(12)

    @JobsScope
    @Provides
    fun provideJobsCategoriesViewAdapter(): CategoriesRecyclerViewAdapter =
        CategoriesRecyclerViewAdapter()

    @JobsScope
    @Provides
    fun providesJobsApi(retrofit: Retrofit): JobsApi = retrofit.create(JobsApi::class.java)

    @JobsScope
    @Provides
    fun providesJobsRepository(jobsApi: JobsApi, categoriesResponse: CategoriesResponse):
            JobsRepository = JobsRepository(jobsApi, categoriesResponse)

    @JobsScope
    @Provides
    fun provideJobsCategories(): CategoriesResponse {
        return Gson().fromJson(
            BaseApplication.applicationContext().getDataFromAssetFile("categories.json"),
            CategoriesResponse::class.java
        )
    }
}