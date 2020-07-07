package com.jovinz.jobsfindingapp.ui.viewmodels

import androidx.lifecycle.*
import com.jovinz.jobsfindingapp.data.ResultData
import com.jovinz.jobsfindingapp.data.jobs.CategoriesResponse
import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import com.jovinz.jobsfindingapp.data.jobs.JobsRepository
import javax.inject.Inject

open class JobsViewModel @Inject constructor(
    private val jobsRepository: JobsRepository
) : ViewModel() {

    var liveDataCategories: LiveData<ResultData<CategoriesResponse?>> =
        jobsRepository.getCategories()
            .asLiveData()


    fun fetchJobs(category: String): LiveData<ResultData<List<JobsByLangResponseItem>?>> {
        return jobsRepository.getJobs(category).asLiveData()
    }

}