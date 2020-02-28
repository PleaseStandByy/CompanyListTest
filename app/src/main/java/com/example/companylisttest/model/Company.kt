package com.example.companylisttest.model

import com.google.gson.annotations.SerializedName

data class Company(
    val id: String,
    @SerializedName("img")
    val imageUrl: String,
    val name: String
)