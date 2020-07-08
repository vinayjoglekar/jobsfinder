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
open class JobsModule {

    @Provides
    fun provideJobsRecyclerViewAdapter(): JobsRecyclerViewAdapter =
        JobsRecyclerViewAdapter()

    @Provides
    fun provideRecyclerViewItemDecoration(): VerticalSpacingItemDecoration =
        VerticalSpacingItemDecoration(12)

    @Provides
    fun provideJobsCategoriesViewAdapter(): CategoriesRecyclerViewAdapter =
        CategoriesRecyclerViewAdapter()

    @Provides
    fun providesJobsApi(retrofit: Retrofit): JobsApi = retrofit.create(JobsApi::class.java)

//    @Provides
//    fun providesJobsRepository(jobsApi: JobsApi, categoriesResponse: CategoriesResponse):
//            JobsRepository = JobsRepository(jobsApi, categoriesResponse)

    @Provides
    fun provideJobsCategories(): CategoriesResponse {
        return Gson().fromJson(
            BaseApplication.applicationContext().getDataFromAssetFile("categories.json"),
            CategoriesResponse::class.java
        )
    }
}