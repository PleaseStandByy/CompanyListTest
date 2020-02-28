package com.example.companylisttest.companyInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.example.companylisttest.R
import com.example.companylisttest.listAdapter.ListAdapter
import com.example.companylisttest.listAdapter.holder.CompanyHolder
import com.example.companylisttest.listAdapter.interfaces.OnItemClickListner
import com.example.companylisttest.model.Company
import kotlinx.android.synthetic.main.fragment_company_list.*
import com.example.companylisttest.companyList.CompanyInfoFragment


class CompanyListFragment : MvpAppCompatFragment(), CompanyListView{

    @InjectPresenter
    lateinit var presenter: CompanyListPresenter

    @ProvidePresenter
    fun providePresenter() = CompanyListPresenter()

    private lateinit var listAdapterCompany : ListAdapter<Company>
    lateinit var sceletonScreen: RecyclerViewSkeletonScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View? = inflater.inflate(R.layout.fragment_company_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapterCompany = ListAdapter(R.layout.item_company,CompanyHolder::class)
        listAdapterCompany.onItemClickListner = object : OnItemClickListner {
            override fun onitemClick(position: Int) {

                val fragment = CompanyInfoFragment()
                val bundle = Bundle()
                bundle.putString(CompanyInfoFragment.BUNDLE_COMPANY_ID, listAdapterCompany.getItem(position).id)
                fragment.arguments = bundle
                fragment.show(activity!!.supportFragmentManager, "")
            }

        }
        recycleViewCompany.layoutManager = LinearLayoutManager(context)
        recycleViewCompany.adapter = listAdapterCompany

        swipeRefreshLayout.setOnRefreshListener {
            presenter.loadCopmanyList()
        }
        sceletonScreen = Skeleton.bind(recycleViewCompany)
            .adapter(listAdapterCompany)
            .load(R.layout.item_skeleton_company)
            .show()

    }

    override fun showCompanyList(list: ArrayList<Company>) {
        listAdapterCompany.setList(list)
    }

    override fun showProgress(isShow: Boolean) {
        if (isShow){
            sceletonScreen.show()
        } else {
            swipeRefreshLayout.isRefreshing = false
            sceletonScreen.hide()
        }
    }

    override fun openCompany(idCompany: String) {

    }
}