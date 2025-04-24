package com.example.lokaljobs.repository

import com.example.lokaljobs.model.Job
import com.example.lokaljobs.network.RetrofitInstance

class JobRepository {
    suspend fun getJobs(page: Int): List<Job>? {
        val response = RetrofitInstance.api.getJobs(page)
        return if (response.isSuccessful) response.body() else null
    }
}
