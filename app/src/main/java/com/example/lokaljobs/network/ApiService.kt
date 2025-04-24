package com.example.lokaljobs.network

import com.example.lokaljobs.model.JobResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("common/jobs")
    suspend fun getJobs(): Response<JobResponse>
}