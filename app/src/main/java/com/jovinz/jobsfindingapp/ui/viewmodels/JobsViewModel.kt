package com.jovinz.jobsfindingapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jovinz.jobsfindingapp.data.ResultData
import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import com.jovinz.jobsfindingapp.network.JobsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JobsViewModel @Inject constructor(private var jobsApi: JobsApi) : ViewModel() {


    fun getJobsData(): LiveData<ResultData<List<JobsByLangResponseItem>?>> {
        return flow {
            emit(ResultData.Loading())
            emit(ResultData.Success(jobsApi.getJobsByLang("python")))
        }.asLiveData(Dispatchers.IO)
    }

}