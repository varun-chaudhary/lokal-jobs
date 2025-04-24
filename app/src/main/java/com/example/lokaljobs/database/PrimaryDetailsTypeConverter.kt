package com.example.lokaljobs.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.lokaljobs.model.PrimaryDetails
import com.google.gson.Gson


@ProvidedTypeConverter
class PrimaryDetailsTypeConverter {

    @TypeConverter
    fun fromPrimaryDetails(primaryDetails: PrimaryDetails): String {
        val gson = Gson()
        return gson.toJson(primaryDetails)
    }

    @TypeConverter
    fun toPrimaryDetails(primaryDetailsString: String): PrimaryDetails {
        val gson = Gson()
        return gson.fromJson(primaryDetailsString, PrimaryDetails::class.java)
    }
}