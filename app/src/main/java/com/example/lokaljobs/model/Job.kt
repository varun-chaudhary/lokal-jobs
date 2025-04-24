package com.example.lokaljobs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.lokaljobs.database.PrimaryDetailsTypeConverter
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "bookmarked_jobs")
@TypeConverters(PrimaryDetailsTypeConverter::class)
data class Job(
    @PrimaryKey val id: Int,
    val title: String?,
    val type: Int?,
    @SerializedName("primary_details") val primaryDetails: PrimaryDetails?,
    @SerializedName("job_type") val jobType: Int?,
    @SerializedName("job_category_id") val jobCategoryId: Int?,
    val qualification: Int?,
    val experience: Int?,
    @SerializedName("shift_timing") val shiftTiming: Int?,
    @SerializedName("job_role_id") val jobRoleId: Int?,
    @SerializedName("salary_max") val salaryMax: Int?,
    @SerializedName("salary_min") val salaryMin: Int?,
    @SerializedName("city_location") val cityLocation: Int?,
    val locality: Int?,
    @SerializedName("premium_till") val premiumTill: String?,
    val content: String?,


    @SerializedName("company_name") val companyName: String?,
    @SerializedName("whatsapp_no") val whatsappNumber: String?,
    var isBookmarked: Boolean? = false
) : Parcelable {
    override fun toString(): String {
        return "Job(title='$title', primaryDetails=$primaryDetails, whatsappNumber='$whatsappNumber')"
    }
}


@Parcelize
data class PrimaryDetails(
    @SerializedName("Place") val place: String?,
    @SerializedName("Salary") val salary: String?,
    @SerializedName("Job_Type") val jobType: String?,
    @SerializedName("Experience") val experience: String?,
    @SerializedName("Fees_Charged") val feesCharged: String?,
    @SerializedName("Qualification") val qualification: String?
) : Parcelable
