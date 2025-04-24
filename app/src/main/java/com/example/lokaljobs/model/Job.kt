package com.example.lokaljobs.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Job(
    val id: Int,
    val title: String?,
    val type: Int?,
    @SerializedName("primary_details") val primaryDetails: PrimaryDetails?,
    val jobTags: List<JobTag>?,
    val jobType: Int?,
    val jobCategoryId: Int?,
    val qualification: Int?,
    val experience: Int?,
    val shiftTiming: Int?,
    val jobRoleId: Int?,
    val salaryMax: Int?,
    val salaryMin: Int?,
    val cityLocation: Int?,
    val locality: Int?,
    val premiumTill: String?,
    val content: String?,


    val companyName: String?,
    val whatsappNumber: String?,
    val createdAt: String?,
    val employerId: Int?,
    val isPremium: Boolean?,
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
