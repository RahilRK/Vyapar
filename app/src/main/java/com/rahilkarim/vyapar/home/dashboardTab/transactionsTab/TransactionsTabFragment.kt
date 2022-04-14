package com.rahilkarim.vyapar.home.dashboardTab.transactionsTab

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahilkarim.skpust.BusinessDetailFrag.transactionListAdapter
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.databinding.FragmentTransactionstabBinding
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.DataClass
import com.rahilkarim.vyapar.util.GlobalClass

class TransactionsTabFragment : Fragment(), transactionListAdapter.transactionListAdapterOnClick {

    var TAG = "TransactionsTabFragment"
    private lateinit var activity: Context

    lateinit var binding: FragmentTransactionstabBinding
    lateinit var globalClass: GlobalClass

    private var arrayList = arrayListOf<transactionListModel>()
    lateinit var listAdapter: transactionListAdapter

    var hasFocus = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = requireContext()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransactionstabBinding.inflate(layoutInflater, container, false)

        init()
        onClick()

        arrayList = DataClass.getTransactions()
        setAdapter(arrayList)

        return binding.root
    }

    fun init() {
        globalClass = (requireActivity().application as Application).globalClass
    }

    fun onClick() {

/*
        binding.edSearchTransaction.setOnFocusChangeListener(object :View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {

                this@TransactionsTabFragment.hasFocus = hasFocus

               */
/* if (hasFocus){
//                    binding.searchParty.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search, 0, R.drawable.ic_close, 0);
                    binding.ivFilterTransaction.visibility = View.GONE
                } else {
                    binding.edSearchTransaction.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search, 0,0, 0);
                    binding.ivFilterTransaction.visibility = View.VISIBLE
                }*//*

            }
        })
*/

        binding.edSearchTransaction.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

                if (s != null && s.isNotEmpty()) {
//                    hasFocus = true
                    binding.ivFilterTransaction.visibility = View.GONE
                    binding.edSearchTransaction.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_search,
                        0,
                        R.drawable.ic_close,
                        0
                    );
                } else if (s != null && s.isEmpty()) {
//                    hasFocus = false
                    binding.edSearchTransaction.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_search,
                        0,
                        0,
                        0
                    );
                    binding.ivFilterTransaction.visibility = View.VISIBLE
//                    binding.searchParty.clearFocus()
//                    globalClass.hideKeyboard(requireActivity())
                }
            }
        })

        binding.edSearchTransaction.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= binding.edSearchTransaction.getRight() - binding.edSearchTransaction.getTotalPaddingRight()) {
                    // your action for drawable click event
                    binding.edSearchTransaction.text.clear()
                    true;
                }
            }
            false;
        })
    }

    fun setAdapter(arrayList: ArrayList<transactionListModel>) {

        val recyclerview = binding.recyclerView
        recyclerview.layoutManager = LinearLayoutManager(activity)
        listAdapter = transactionListAdapter(activity, arrayList, this)
        recyclerview.adapter = listAdapter
    }

    override fun viewDetail(pos: Int, listModel: transactionListModel) {
        TODO("Not yet implemented")
    }

    override fun menuOnClick(pos: Int, listModel: transactionListModel) {
        TODO("Not yet implemented")
    }
}