package com.jovinz.jobsfindingapp.network

import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {

    @GET("positions.json")
    suspend fun getJobsByLang(@Query("description") description: String): List<JobsByLangResponseItem>

    @GET("positions.json")
    suspend fun getJobsByLangFlow(@Query("description") description: String,@Query("page") pageNumber: Int): List<JobsByLangResponseItem>
}