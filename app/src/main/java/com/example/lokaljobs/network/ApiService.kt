package com.example.lokaljobs.network

import com.example.lokaljobs.model.Job
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("common/jobs")
    suspend fun getJobs(@Query("page") page: Int): Response<List<Job>>
}