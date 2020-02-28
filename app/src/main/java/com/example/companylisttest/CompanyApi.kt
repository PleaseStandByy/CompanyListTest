package com.example.companylisttest

import com.example.companylisttest.model.Company
import retrofit2.http.GET
import retrofit2.http.Query

interface CompanyApi {

    @GET("test.php")
    suspend fun getCompanyList() : ArrayList<Company>

    @GET("test.php")
    suspend fun getCompany(@Query("id") id: String) : Company
}