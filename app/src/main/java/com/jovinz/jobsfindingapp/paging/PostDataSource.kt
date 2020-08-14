package com.jovinz.jobsfindingapp.paging

import androidx.paging.PagingSource
import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import com.jovinz.jobsfindingapp.network.JobsApi
import okio.IOException
import retrofit2.HttpException
import java.lang.Exception

class PostDataSource(private val apiService: JobsApi, val category: String) :
    PagingSource<Int, JobsByLangResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobsByLangResponseItem> {

        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getJobsByLangFlow(category, currentLoadingPageKey)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            if (response.isEmpty()) {
                return if (currentLoadingPageKey == 1)
                    LoadResult.Error(Exception())
                else {
                    LoadResult.Page(emptyList(), null, null, 0, 0)
                }
            }

            return LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}

