package com.jovinz.jobsfindingapp.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jovinz.jobsfindingapp.data.ResultData
import com.jovinz.jobsfindingapp.data.jobs.CategoriesResponse
import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import com.jovinz.jobsfindingapp.data.jobs.JobsRepository
import com.jovinz.jobsfindingapp.network.JobsApi
import com.jovinz.jobsfindingapp.paging.PostDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class JobsViewModel @Inject constructor(
    private val jobsApi: JobsApi,
    private var categoriesResponse: CategoriesResponse
) : ViewModel() {

//    fun fetchJobs(category: String): LiveData<ResultData<List<JobsByLangResponseItem>?>> {
//        return flow {
//            emit(ResultData.Loading())
//            val jobsList = jobsApi.getJobsByLangFlow(category)
////                .filter {
////                    it.location?.contains("UK")!!
////                }
//            if (jobsList.isEmpty()) emit(ResultData.Failed("No Items Found"))
//            else emit(ResultData.Success(jobsList))
//        }.asLiveData(Dispatchers.IO)
//    }

    fun getCategories(): LiveData<ResultData<CategoriesResponse?>> {
        return flow {
            emit(ResultData.Loading())
            emit(ResultData.Success(categoriesResponse))
        }.asLiveData(Dispatchers.IO)
    }

    fun fetchJobs(category: String): Flow<PagingData<JobsByLangResponseItem>> {
        return Pager(PagingConfig(pageSize = 6)) {
            PostDataSource(jobsApi, category)
        }.flow
    }


}