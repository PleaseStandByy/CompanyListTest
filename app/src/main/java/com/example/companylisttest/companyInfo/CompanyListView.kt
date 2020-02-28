package com.example.companylisttest.companyInfo

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.companylisttest.model.Company

interface CompanyListView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showCompanyList(list: ArrayList<Company>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgress(isShow: Boolean)

    fun openCompany(idCompany: String)
}