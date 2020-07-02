package com.jovinz.jobsfindingapp.network

import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {

    @GET("positions.json")
    suspend fun getJobsByLang(@Query("description") description: String): List<JobsByLangResponseItem>
}