package com.example.lokaljobs.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Job(
    val id: Int?,
    val title: String?,
    val type: Int?,
    @SerializedName("primary_details") val primaryDetails: PrimaryDetails?,
    @SerializedName("job_tags") val jobTags: List<JobTag>?,
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
    val isBookmarked: Boolean?
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

@Parcelize
data class JobTag(
    val value: String,
    @SerializedName("bg_color") val bgColor: String?,
    @SerializedName("text_color") val textColor: String?
) : Parcelable
