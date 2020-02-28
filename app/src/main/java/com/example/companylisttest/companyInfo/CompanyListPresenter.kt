package com.example.companylisttest.companyInfo

import android.app.Application
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.companylisttest.MyApp
import com.example.companylisttest.CompanyApi
import com.example.companylisttest.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@InjectViewState
class CompanyListPresenter : MvpPresenter<CompanyListView>() {

    @Inject
    protected lateinit var api: CompanyApi

    @Inject
    protected  lateinit var app: Application

    init{
        MyApp.getAppComponent().inject(this)
    }
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCopmanyList()
    }

    fun loadCopmanyList(){
        viewState.showProgress(true)
        CoroutineScope(IO).launch{
            try {
                val list = api.getCompanyList()
                withContext(Main){
                    viewState.showProgress(false)
                    viewState.showCompanyList(list)
                }
            } catch (e: Exception ){
                withContext(Main){
                    viewState.showMessage(app.getString(R.string.error))
                }
            }
        }
    }
}