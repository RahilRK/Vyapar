package com.rahilkarim.vyapar.itemDetail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahilkarim.skpust.BusinessDetailFrag.itemTransactionsListAdapter
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.databinding.FragmentItemDetailBinding
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.DataClass
import com.rahilkarim.vyapar.util.GlobalClass

class ItemDetailFragment : Fragment(),itemTransactionsListAdapter.itemDetailitemListAdapterOnClick {

    var TAG = "ItemDetailFragment"
    private lateinit var activity: Context

    lateinit var binding: FragmentItemDetailBinding
    lateinit var globalClass: GlobalClass

    val args: ItemDetailFragmentArgs by navArgs()

    lateinit var toolbar: androidx.appcompat.widget.Toolbar;

    private var arrayList = arrayListOf<itemTransactionsModel>()
    lateinit var adapter: itemTransactionsListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = requireContext()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemDetailBinding.inflate(layoutInflater, container, false)

        init()
        setToolbar()
        setText()
//        onClick()

        arrayList = DataClass.getItemTransactions()
        setAdapter(arrayList)

        globalClass.log(TAG,args.itemModelArgs.toString())
        return binding.root
    }

    fun init() {
        globalClass = (requireActivity().application as Application).globalClass
    }

    fun setToolbar() {
        toolbar = binding.toolbar
        toolbar.title = resources.getString(R.string.item_detail)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            getActivity()?.onBackPressed()
        }
    }

    fun setText() {

        val itemModel = args.itemModelArgs

        binding.itemName.text = itemModel.itemName
        binding.salesPrice.text =
            activity.resources.getString(R.string.enter_amount,itemModel.salesPrice.toString())
        binding.purchasePrice.text =
            activity.resources.getString(R.string.enter_amount,itemModel.purchasePrice.toString())
        binding.inStock.text = itemModel.inStock.toString()

        val getStockValue = itemModel.purchasePrice * itemModel.inStock
        binding.stockValue.text =
            activity.resources.getString(R.string.enter_amount,getStockValue.toString())
    }

    fun setAdapter(arrayList: ArrayList<itemTransactionsModel>) {

        val recyclerview = binding.recyclerView
        recyclerview.layoutManager = LinearLayoutManager(activity)
        adapter = itemTransactionsListAdapter(activity, arrayList, this)
        recyclerview.adapter = adapter
    }

    override fun viewDetail(pos: Int, model: itemTransactionsModel) {

    }
}