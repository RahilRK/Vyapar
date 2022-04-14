package com.rahilkarim.skpust.BusinessDetailFrag

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.databinding.MonthsummaryItemBinding
import com.rahilkarim.vyapar.home.dashboard.monthSummaryModel
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.GlobalClass

class monthSummaryAdapter(
    private val activity: Context,
    private val list: ArrayList<monthSummaryModel>,
) : RecyclerView.Adapter<monthSummaryAdapter.ViewHolder>() {

    var globalClass: GlobalClass = GlobalClass.getInstance(activity)
    var tag = "monthSummaryAdapter"
    lateinit var binding: MonthsummaryItemBinding



    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        binding = MonthsummaryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = list[position]
        Glide.with(activity)
            .load(model.icon)
            .into(binding.ivIcon)
        binding.ivIcon.rotation = model.rotateDegree.toFloat()
//        binding.ivIcon.ImageViewCompat.imageTintList(model.iconColor)
        globalClass.setTint(binding.ivIcon,model.iconColor)

        binding.title.text = model.title
        binding.amount.text =
            activity.resources.getString(R.string.enter_amount,model.amount.toString())
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }
}