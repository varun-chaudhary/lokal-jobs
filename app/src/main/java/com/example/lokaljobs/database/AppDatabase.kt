package com.example.lokaljobs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lokaljobs.dao.JobDao

import com.example.lokaljobs.model.Job

@Database(entities = [Job::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "job_database"
                ).addTypeConverter(PrimaryDetailsTypeConverter())
                    .build().also { INSTANCE = it }
            }
        }
    }
}