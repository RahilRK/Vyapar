package com.rahilkarim.vyapar

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.Constant
import com.rahilkarim.vyapar.util.GlobalClass
import com.rahilkarim.vyapar.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    var TAG = "SignUpFragment"
    private lateinit var activity: Context

    lateinit var globalClass: GlobalClass
    lateinit var binding: FragmentSignUpBinding

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
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)

        init()
        onClick()

        if(globalClass.debug()) {

            binding.mobileNumber.setText(Constant.rahilMobileNumber)
        }

        return binding.root
    }

    fun init() {
//        globalClass = GlobalClass.getInstance(activity)
        globalClass = (requireActivity().application as Application).globalClass
    }

    fun onClick() {

        binding.mobileNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                binding.getotpbt.isEnabled = s != null && s.length > 0
            }
        })

        binding.getotpbt.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_verifyOTPFragment)
        }
    }
}