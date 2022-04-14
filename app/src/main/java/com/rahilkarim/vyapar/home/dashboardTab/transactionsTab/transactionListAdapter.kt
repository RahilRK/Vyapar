package com.rahilkarim.skpust.BusinessDetailFrag

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.databinding.TransactionlistItemBinding
import com.rahilkarim.vyapar.home.dashboardTab.transactionsTab.transactionListModel

class transactionListAdapter(
    private val activity: Context,
    private val list: ArrayList<transactionListModel>,
    private val onClick: transactionListAdapterOnClick,
) : RecyclerView.Adapter<transactionListAdapter.ViewHolder>() {

    var tag = "transactionListAdapter"
    lateinit var binding: TransactionlistItemBinding

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        binding = TransactionlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = list[position]
        binding.partyName.text = model.partyName
        binding.total.text =
            activity.resources.getString(R.string.enter_amount,model.total.toString())
        binding.balance.text =
            activity.resources.getString(R.string.enter_amount,model.balance.toString())

        binding.date.text = "${model.transactionDate} ${model.transactionTime}"

        binding.transactionType.text = model.transactionType

        if(model.transactionType == "Sale") {
            binding.transactionType.setTextColor(ContextCompat.getColor(activity, R.color.successColor))
            binding.transactionType.setBackground(ContextCompat.getDrawable(activity, R.drawable.rounded_curve_greenbg))
        }
        else {
            binding.transactionType.setTextColor(ContextCompat.getColor(activity, R.color.warningColor))
            binding.transactionType.setBackground(ContextCompat.getDrawable(activity, R.drawable.rounded_curve_redbg))
        }
        binding.transactionType.setPadding(12,12,12,12)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }

    public interface transactionListAdapterOnClick {

        fun viewDetail(pos: Int, listModel: transactionListModel)

        fun menuOnClick(pos: Int, listModel: transactionListModel)
    }
}