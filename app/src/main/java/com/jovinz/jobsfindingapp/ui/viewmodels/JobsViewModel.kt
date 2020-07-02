package com.jovinz.jobsfindingapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jovinz.jobsfindingapp.network.JobsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JobsViewModel @Inject constructor( private var jobsApi: JobsApi) : ViewModel() {

    fun getJobsData(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                jobsApi.getJobsByLang("python")
            }
        }
    }

}