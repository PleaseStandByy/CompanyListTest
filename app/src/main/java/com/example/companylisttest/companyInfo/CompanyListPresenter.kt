package com.example.companylisttest.companyInfo

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.companylisttest.MyApp
import com.example.companylisttest.CompanyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@InjectViewState
class CompanyListPresenter : MvpPresenter<CompanyListView>() {

    @Inject
    protected lateinit var api: CompanyApi

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
            val list = api.getCompanyList()
            withContext(Main){
                viewState.showProgress(false)
                viewState.showCompanyList(list)
            }
        }
    }
}