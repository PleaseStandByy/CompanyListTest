package com.example.companylisttest

import android.app.Application
import com.example.companylisttest.di.AppComponent
import com.example.companylisttest.di.DaggerAppComponent
import com.example.companylisttest.di.module.AppModule
import com.example.companylisttest.di.module.NetModule


class MyApp : Application() {

    companion object {
        private lateinit var appComponent: AppComponent
        fun getAppComponent() : AppComponent = appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .netModule(NetModule())
            .appModule(AppModule(this))
            .build()
    }
}