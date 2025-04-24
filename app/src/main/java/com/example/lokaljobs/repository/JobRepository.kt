package com.example.lokaljobs.repository

import com.example.lokaljobs.model.JobResponse
import com.example.lokaljobs.network.RetrofitInstance

class JobRepository {
    suspend fun getJobs(): JobResponse? {
        val response = RetrofitInstance.api.getJobs()
        return if (response.isSuccessful) response.body() else null
    }
}
