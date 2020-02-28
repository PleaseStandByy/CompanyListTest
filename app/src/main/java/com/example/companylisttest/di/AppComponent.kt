package com.example.companylisttest.di


import com.example.companylisttest.companyInfo.CompanyListPresenter
import com.example.companylisttest.companyList.CompanyInfoPresenter
import com.example.companylisttest.di.module.AppModule
import com.example.companylisttest.di.module.NetModule
import dagger.Component

@Component(modules = [NetModule::class, AppModule::class])
interface AppComponent {

    fun inject(presenter: CompanyListPresenter)

    fun inject(presenter: CompanyInfoPresenter)

}