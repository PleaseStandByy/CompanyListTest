package com.example.companylisttest.companyList

import android.app.Application
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.companylisttest.CompanyApi
import com.example.companylisttest.MyApp
import com.example.companylisttest.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@InjectViewState
class CompanyInfoPresenter(var companyId: String): MvpPresenter<CompanyInfoView>() {

    @Inject
    protected lateinit var api: CompanyApi

    @Inject
    protected lateinit var app: Application

    init{
        MyApp.getAppComponent().inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadInfoCompany()
    }

    fun loadInfoCompany(){
        CoroutineScope(IO).launch {
            try {
                val info  = api.getCompany(companyId)
                withContext(Main) {
                    viewState.showInfo(info[0])
                }
            } catch (e: Exception){
                withContext(Main){
                    viewState.showMessage(app.getString(R.string.error))
                }
            }

        }
    }
}