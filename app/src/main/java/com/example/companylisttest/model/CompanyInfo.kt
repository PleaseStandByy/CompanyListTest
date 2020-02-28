package com.example.companylisttest.model

import com.google.gson.annotations.SerializedName

data class CompanyInfo(
    val description: String?,
    val id: String?,
    @SerializedName("img")
    val imageUrl: String?,
    val lat: Double?,
    val lon: Double?,
    val name: String?,
    val phone: String?,
    @SerializedName("www")
    val website: String?
)