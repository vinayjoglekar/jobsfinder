package com.jovinz.jobsfindingapp.data.jobs

import com.jovinz.jobsfindingapp.data.ResultData
import com.jovinz.jobsfindingapp.network.JobsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class JobsRepository @Inject constructor(
    private val jobsApi: JobsApi,
    private var categoriesResponse: CategoriesResponse
) {

//    fun getJobs(category: String): Flow<ResultData<List<JobsByLangResponseItem>?>> {
//        return flow {
//            emit(ResultData.Loading())
//            val jobsList = jobsApi.getJobsByLangFlow(category)
////                .filter {
////                    it.location?.contains("UK")!!
////                }
//            if (jobsList.isEmpty()) emit(ResultData.Failed("No Items Found"))
//            else emit(ResultData.Success(jobsList))
//        }.flowOn(Dispatchers.IO)
//    }

    fun getCategories(): Flow<ResultData<CategoriesResponse?>> {
        return flow {
            emit(ResultData.Loading())
            emit(ResultData.Success(categoriesResponse))
        }.flowOn(Dispatchers.IO)
    }

}