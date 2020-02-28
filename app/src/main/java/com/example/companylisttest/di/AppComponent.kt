package com.example.companylisttest.di


import com.example.companylisttest.di.module.AppModule
import com.example.companylisttest.di.module.NetModule
import dagger.Component

@Component(modules = [NetModule::class, AppModule::class])
interface AppComponent {

}