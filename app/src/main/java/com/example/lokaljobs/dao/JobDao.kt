package com.example.lokaljobs.dao

import androidx.room.*
import com.example.lokaljobs.model.Job

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: Job)

    @Delete
    suspend fun deleteJob(job: Job)

    @Query("SELECT * FROM bookmarked_jobs")
    fun getAllBookmarkedJobs(): kotlinx.coroutines.flow.Flow<List<Job>>

    @Query("SELECT * FROM bookmarked_jobs WHERE id = :id")
    suspend fun getJobById(id: Int): Job

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_jobs WHERE id = :jobId)")
    suspend fun isBookmarked(jobId: String): Boolean

    @Query("DELETE FROM bookmarked_jobs WHERE id = :jobId")
    suspend fun deleteJobById(jobId: String)


    @Query("SELECT EXISTS(SELECT * FROM bookmarked_jobs WHERE id = :jobId)")
    suspend fun isJobBookmarked(jobId: Int): Boolean
}