package com.rahilkarim.vyapar.home.dashboardTab.partiesTab

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahilkarim.skpust.BusinessDetailFrag.partiesListAdapter
import com.rahilkarim.vyapar.databinding.FragmentPartiestabBinding
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.DataClass
import com.rahilkarim.vyapar.util.GlobalClass


class PartiesTabFragment : Fragment(),partiesListAdapter.partiesListAdapterOnClick {

    var TAG = "PartiesTabFragment"
    private lateinit var activity: Context

    lateinit var binding: FragmentPartiestabBinding
    lateinit var globalClass: GlobalClass

    private var arrayList = arrayListOf<partiesListModel>()
    lateinit var listAdapter: partiesListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = requireContext()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPartiestabBinding.inflate(layoutInflater, container, false)

        init()

        arrayList = DataClass.getParties()
        setAdapter(arrayList)

        return binding.root
    }

    fun init() {
        globalClass = (requireActivity().application as Application).globalClass
    }

    fun setAdapter(arrayList: ArrayList<partiesListModel>) {

        val recyclerview = binding.partiesRecyclerView
        recyclerview.layoutManager = LinearLayoutManager(activity)
        listAdapter = partiesListAdapter(activity, arrayList, this)
        recyclerview.adapter = listAdapter
    }

    override fun viewDetail(pos: Int, listModel: partiesListModel) {

    }
}