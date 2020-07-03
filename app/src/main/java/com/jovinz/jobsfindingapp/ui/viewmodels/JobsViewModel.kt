package com.jovinz.jobsfindingapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jovinz.jobsfindingapp.data.ResultData
import com.jovinz.jobsfindingapp.data.jobs.CategoriesResponse
import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import com.jovinz.jobsfindingapp.network.JobsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JobsViewModel @Inject constructor(
    private var jobsApi: JobsApi,
    private var categoriesResponse: CategoriesResponse
) : ViewModel() {


    var liveData: LiveData<ResultData<List<JobsByLangResponseItem>?>> = MutableLiveData()
    var liveDataCategories: LiveData<ResultData<CategoriesResponse?>> = MutableLiveData()

    fun getJobsData(): LiveData<ResultData<List<JobsByLangResponseItem>?>> {

        liveData = flow {
            emit(ResultData.Loading())
            emit(ResultData.Success(jobsApi.getJobsByLang("python")))
        }.asLiveData(Dispatchers.IO)
        return liveData
    }

    fun getCategoriesData(): LiveData<ResultData<CategoriesResponse?>> {
        liveDataCategories = flow {
            emit(ResultData.Loading())
            emit(ResultData.Success(categoriesResponse))
        }.asLiveData(Dispatchers.IO)
        return liveDataCategories
    }


}