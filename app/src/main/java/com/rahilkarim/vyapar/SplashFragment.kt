package com.rahilkarim.vyapar

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.Constant.Companion.splashScreenTime
import com.rahilkarim.vyapar.util.GlobalClass
import com.rahilkarim.vyapar.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    var TAG = "SplashFragment"
    private lateinit var activity: Context

    lateinit var binding: FragmentSplashBinding
    lateinit var globalClass: GlobalClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = requireContext()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)

        init()
        nextScreen()

        return binding.root
    }

    fun init() {
        globalClass = (requireActivity().application as Application).globalClass
    }

    fun nextScreen() {

        Handler(Looper.myLooper()!!).postDelayed(
            {
                if(globalClass.getString("mobileNumber") == "") {
                    findNavController().navigate(R.id.action_splashScreenFragment_to_signUpFragment)
                }
                else {
                    findNavController().navigate(R.id.action_splashScreenFragment_to_signUpFragment)
                }
            },splashScreenTime)
    }
}