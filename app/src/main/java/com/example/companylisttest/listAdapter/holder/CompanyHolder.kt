package com.example.companylisttest.listAdapter.holder

import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.companylisttest.R
import com.example.companylisttest.listAdapter.AbsHolder
import com.example.companylisttest.model.Company
import com.example.companylisttest.di.module.NetModule
import de.hdodenhof.circleimageview.CircleImageView


class CompanyHolder(itemView: View) : AbsHolder(itemView) {

    private val textViewCompanyName = itemView.findViewById<TextView>(R.id.company_name)

    private var imageViewCompany = itemView.findViewById<CircleImageView>(R.id.company_image)
    override fun bind(item: Any?) {
        item as Company
        textViewCompanyName.text = item.name

        Glide.with(itemView)
            .load(NetModule.BASE_URL + item.image)
            .centerCrop()
            .into(imageViewCompany)
        itemView.setOnClickListener {
            onItemClickListner!!.onitemClick(adapterPosition)
        }
    }

}