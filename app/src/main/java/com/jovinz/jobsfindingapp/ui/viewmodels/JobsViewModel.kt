package com.jovinz.jobsfindingapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jovinz.jobsfindingapp.data.ResultData
import com.jovinz.jobsfindingapp.data.jobs.CategoriesResponse
import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import com.jovinz.jobsfindingapp.network.JobsApi
import com.jovinz.jobsfindingapp.paging.PostDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class JobsViewModel @Inject constructor(
    private val jobsApi: JobsApi,
    private var categoriesResponse: CategoriesResponse
) : ViewModel() {

    @Deprecated("")
    fun fetchJobs(category: String): LiveData<ResultData<List<JobsByLangResponseItem>?>> {
        return flow {
            emit(ResultData.Loading())
            val jobsList = jobsApi.getJobsByLangFlow(category)
            if (jobsList.isEmpty()) emit(ResultData.Failed("No Items Found"))
//            else emit(ResultData.Success(jobsList))
        }.asLiveData(Dispatchers.IO)
    }

    fun getCategories(): LiveData<ResultData<CategoriesResponse?>> {
        return flow {
            emit(ResultData.Loading())
            emit(ResultData.Success(categoriesResponse))
        }.asLiveData(Dispatchers.IO)
    }

    fun fetchPagedJobs(category: String): Flow<PagingData<JobsByLangResponseItem>> {
        return Pager(PagingConfig(pageSize = 6)) {
            PostDataSource(jobsApi, category)
        }.flow
    }


}