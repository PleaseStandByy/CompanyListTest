package com.example.companylisttest.di.module

import dagger.Module
import android.app.Application
import dagger.Provides

@Module
class AppModule(application: Application) {

    var app: Application = application

    @Provides
    fun providesApplication(): Application = app
}
