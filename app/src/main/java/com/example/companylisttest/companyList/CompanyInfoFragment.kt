package com.example.companylisttest.companyList

import android.content.Intent
import android.net.Uri
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.example.companylisttest.model.CompanyInfo
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.companylisttest.R
import com.example.companylisttest.di.module.NetModule
import kotlinx.android.synthetic.main.fragment_dialog_info_company.*


class CompanyInfoFragment : MvpAppCompatDialogFragment(), CompanyInfoView {

    companion object {
        val BUNDLE_COMPANY_ID = "companyId"
    }

    @InjectPresenter
    lateinit var presenter: CompanyInfoPresenter

    @ProvidePresenter
    fun providePresenter() = CompanyInfoPresenter(arguments!!.getString(BUNDLE_COMPANY_ID)!!)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dialog_info_company, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun showInfo(info: CompanyInfo) {
        companyFullImage.visibility = VISIBLE
        nameCompany.visibility = VISIBLE
        description.visibility = VISIBLE
        website.visibility = VISIBLE
        phone.visibility = VISIBLE
        buttonMap.visibility = VISIBLE

        Glide.with(this)
            .load(NetModule.BASE_URL + info.imageUrl)
            .centerCrop()
            .into(companyFullImage)
        if(info.name != null ) {
            if (info.name.isNotEmpty()) {
                nameCompany.setText(info.name)
            } else {
                nameCompany.visibility = GONE
            }
        } else {
            nameCompany.visibility = GONE
        }

        if(info.description != null ) {
            if (info.description.isNotEmpty()) {
                description.text = info.description
            } else {
                description.visibility = GONE
            }
        } else {
            description.visibility = GONE
        }

        if(info.phone != null ) {
            if (info.phone.isNotEmpty()) {
                phone.text = getString(R.string.phone) + ": " +info.phone
            } else {
                phone.visibility = GONE
            }
        } else {
            phone.visibility = GONE
        }

        if(info.website != null ) {
            if (info.website.isNotEmpty()) {
                website.text = getString(R.string.website) + ": " +info.website
            } else {
                website.visibility = GONE
            }
        } else {
            website.visibility = GONE
        }

        if(info.lon != null && info.lat!= null ) {
            buttonMap.setOnClickListener{ val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.openstreetmap.org/#map=8/"
                    + info.lat
                    + "/"
                    + info.lon))
                startActivity(browserIntent)
            }
        } else {
            buttonMap.visibility = GONE
        }
    }

    override fun showMessage(text: String) {
        message.visibility = VISIBLE
        message.text = text
    }
}
