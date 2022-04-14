package com.rahilkarim.vyapar.home.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.rahilkarim.skpust.BusinessDetailFrag.monthSummaryAdapter
import com.rahilkarim.vyapar.home.dashboardTab.itemsTab.ItemsTabFragment
import com.rahilkarim.vyapar.home.dashboardTab.partiesTab.PartiesTabFragment
import com.rahilkarim.vyapar.home.dashboardTab.transactionsTab.TransactionsTabFragment
import com.rahilkarim.vyapar.home.HomeActivity
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.DataClass
import com.rahilkarim.vyapar.util.GlobalClass
import com.rahilkarim.vyapar.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(), LifecycleObserver {

    var TAG = "DashboardFragment"
//    private lateinit var activity: Context

    lateinit var binding: FragmentDashboardBinding
    lateinit var globalClass: GlobalClass

    var fragmentPagerItemAdapter: FragmentPagerItemAdapter? = null

    var arrayList = arrayListOf<monthSummaryModel>()
    lateinit var adapter: monthSummaryAdapter;

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onCreated(){

        try {

            (activity as HomeActivity?)?.setToolbar(binding.toolbar)
        }
        catch (e: Exception) {

            val error = Log.getStackTraceString(e)
            globalClass.log(TAG,error)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)

        init()
        setDashboardTab()

        arrayList = DataClass.getMonthSummary()
        setMonthSummaryAdapter(arrayList)

        return binding.root
    }

    fun init() {
        globalClass = (requireActivity().application as Application).globalClass
    }

    fun setDashboardTab() {

        /* val tabTitles = arrayOf(
             getString(R.string.parties),
             getString(R.string.transactions),
             getString(R.string.items))

         binding.viewPager.adapter = ViewPagerFragmentAdapter(requireActivity())

         // attaching tab mediator
         TabLayoutMediator(
             binding.tabLayout, binding.viewPager
         ) { tab: TabLayout.Tab, position: Int -> tab.setText(tabTitles.get(position)) }.attach()*/

        try {
            fragmentPagerItemAdapter = FragmentPagerItemAdapter(
                this.childFragmentManager, FragmentPagerItems.with(activity)
                    .add(getString(R.string.parties), PartiesTabFragment::class.java)
                    .add(getString(R.string.transactions), TransactionsTabFragment::class.java)
                    .add(getString(R.string.items), ItemsTabFragment::class.java)
                    .create()
            )

            binding.viewPager.setAdapter(fragmentPagerItemAdapter)
            binding.viewpagertab.setViewPager(binding.viewPager)
        }
        catch (e: Exception) {

            val error = Log.getStackTraceString(e)
            globalClass.log(TAG,error)
        }
    }

    fun setMonthSummaryAdapter(arrayList: ArrayList<monthSummaryModel>) {

        try {
            globalClass.log(TAG, "setMonthSummaryAdapter: ${arrayList.size}")
            val recyclerview = binding.monthSummaryRecyclerView
            recyclerview.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,false)
            adapter = monthSummaryAdapter(requireActivity(), arrayList)
            recyclerview.adapter = adapter
        }
        catch (e: Exception) {

            val error = Log.getStackTraceString(e)
            globalClass.log(TAG,error)
        }
    }

    override fun onDetach() {
        super.onDetach()
        lifecycle.removeObserver(this)
    }

/*
    private class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return PartiesTabFragment()
                1 -> return TransactionsTabFragment()
                2 -> return ItemsTabFragment()
            }
            return PartiesTabFragment()
        }

        override fun getItemCount(): Int {
            return 3
        }
    }
*/
}