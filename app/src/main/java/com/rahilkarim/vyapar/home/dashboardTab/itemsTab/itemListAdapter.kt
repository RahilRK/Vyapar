package com.rahilkarim.skpust.BusinessDetailFrag

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.databinding.ItemlistItemBinding
import com.rahilkarim.vyapar.home.dashboardTab.itemsTab.itemModel

class itemListAdapter(
    private val activity: Context,
    private val list: ArrayList<itemModel>,
    private val onClick: itemListAdapterOnClick,
) : RecyclerView.Adapter<itemListAdapter.ViewHolder>() {

    var tag = "itemListAdapter"
    lateinit var binding: ItemlistItemBinding

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        binding = ItemlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = list[position]
        binding.itemName.text = model.itemName
        binding.salesPrice.text =
            activity.resources.getString(R.string.enter_amount,model.salesPrice.toString())
        binding.purchasePrice.text =
            activity.resources.getString(R.string.enter_amount,model.purchasePrice.toString())
        binding.inStock.text = model.inStock.toString()
        binding.itemListParentLayout.setOnClickListener {
            onClick.viewDetail(position,model)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }

    public interface itemListAdapterOnClick {

        fun viewDetail(pos: Int, model: itemModel)
    }
}