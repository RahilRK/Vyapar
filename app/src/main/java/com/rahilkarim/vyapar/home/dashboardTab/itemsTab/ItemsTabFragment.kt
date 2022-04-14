package com.rahilkarim.vyapar.home.dashboardTab.itemsTab

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahilkarim.skpust.BusinessDetailFrag.itemListAdapter
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.databinding.FragmentItemstabBinding
import com.rahilkarim.vyapar.home.dashboard.DashboardFragmentDirections
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.DataClass
import com.rahilkarim.vyapar.util.GlobalClass

class ItemsTabFragment : Fragment(),itemListAdapter.itemListAdapterOnClick {

    var TAG = "ItemsTabFragment"
    private lateinit var activity: Context

    lateinit var binding: FragmentItemstabBinding
    lateinit var globalClass: GlobalClass

    private var arrayList = arrayListOf<itemModel>()
    lateinit var listAdapter: itemListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = requireContext()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemstabBinding.inflate(layoutInflater, container, false)

        init()
        onClick()

        arrayList = DataClass.getItems()
        setAdapter(arrayList)

        return binding.root
    }

    fun init() {
        globalClass = (requireActivity().application as Application).globalClass
    }

    fun onClick() {
        binding.addNewItembt.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addItemFragment)
        }
    }

    fun setAdapter(array: ArrayList<itemModel>) {

        val recyclerview = binding.recyclerView
        recyclerview.layoutManager = LinearLayoutManager(activity)
        listAdapter = itemListAdapter(activity, array, this)
        recyclerview.adapter = listAdapter
    }

    override fun viewDetail(pos: Int, model: itemModel) {

        val action = DashboardFragmentDirections.actionDashboardFragmentToItemDetailFragment(model)
        findNavController().navigate(action)
    }
}