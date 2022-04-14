package com.rahilkarim.skpust.BusinessDetailFrag

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.databinding.ItemtransactionslistItemBinding
import com.rahilkarim.vyapar.itemDetail.itemTransactionsModel

class itemTransactionsListAdapter(
    private val activity: Context,
    private val list: ArrayList<itemTransactionsModel>,
    private val onClick: itemDetailitemListAdapterOnClick,
) : RecyclerView.Adapter<itemTransactionsListAdapter.ViewHolder>() {

    var tag = "itemTransactionsListAdapter"
    lateinit var binding: ItemtransactionslistItemBinding

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        binding = ItemtransactionslistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = list[position]
        binding.transactionType.text = model.transactionType
        binding.transactionDate.text = model.transactionDate
        binding.quantity.text = model.quantity.toString()
        binding.totalAmount.text =
            activity.resources.getString(R.string.enter_amount,model.totalAmount.toString())
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }

    public interface itemDetailitemListAdapterOnClick {

        fun viewDetail(pos: Int, model: itemTransactionsModel)
    }
}