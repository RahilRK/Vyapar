package com.rahilkarim.skpust.BusinessDetailFrag

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.databinding.PartieslistItemBinding
import com.rahilkarim.vyapar.home.dashboardTab.partiesTab.partiesListModel

class partiesListAdapter(
    private val activity: Context,
    private val list: ArrayList<partiesListModel>,
    private val onClick: partiesListAdapterOnClick,
) : RecyclerView.Adapter<partiesListAdapter.ViewHolder>() {

    var tag = "partiesAdapter"
    lateinit var binding: PartieslistItemBinding

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        binding = PartieslistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = list[position]
        binding.partyName.text = model.partyName
        binding.amount.text =
            activity.resources.getString(R.string.enter_amount,model.amount.toString())
        binding.lastPaymentDate.text = model.lastPaymentDate
        if(model.isGet) {
            binding.amount.setTextColor(ContextCompat.getColor(activity, R.color.successColor))
            binding.giveGet.text = activity.getString(R.string.youwillget)
            binding.giveGet.setTextColor(ContextCompat.getColor(activity, R.color.successColor))
        }
        else {
            binding.amount.setTextColor(ContextCompat.getColor(activity, R.color.warningColor))
            binding.giveGet.text = activity.getString(R.string.youwillgive)
            binding.giveGet.setTextColor(ContextCompat.getColor(activity, R.color.warningColor))
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }

    public interface partiesListAdapterOnClick {

        fun viewDetail(pos: Int, listModel: partiesListModel)
    }
}