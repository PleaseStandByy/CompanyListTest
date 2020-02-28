package com.example.companylisttest.companyList

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.companylisttest.model.CompanyInfo

interface CompanyInfoView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showInfo(info: CompanyInfo)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showMessage(text: String)
}