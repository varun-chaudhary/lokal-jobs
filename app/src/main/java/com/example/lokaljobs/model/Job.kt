package com.example.lokaljobs.model

data class Job(
    val id: Int,
    val title: String,
    val whatsapp_no: String,
    val primary_details: PrimaryDetails,
    val job_tags: List<JobTag>?

)

data class PrimaryDetails(
    val Place: String?,
    val Salary: String?,
    val Job_Type: String?,
    val Experience: String?,
    val Fees_Charged: String?,
    val Qualification: String?
)

data class JobTag(
    val value: String,
    val bg_color: String,
    val text_color: String
)
