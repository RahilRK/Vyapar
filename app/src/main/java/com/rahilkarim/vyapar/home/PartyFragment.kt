package com.rahilkarim.vyapar.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.GlobalClass
import com.rahilkarim.vyapar.databinding.FragmentPartyBinding

class PartyFragment : Fragment(), LifecycleObserver {

    var TAG = "PartyFragment"
//    private lateinit var activity: Context

    lateinit var binding: FragmentPartyBinding
    lateinit var globalClass: GlobalClass

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onCreated(){
        (activity as HomeActivity?)?.setToolbar(binding.toolbar)
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
        binding = FragmentPartyBinding.inflate(layoutInflater, container, false)

        init()

        return binding.root
    }

    fun init() {
        globalClass = (requireActivity().application as Application).globalClass
    }

    override fun onDetach() {
        super.onDetach()
        lifecycle.removeObserver(this)
    }
}