package com.rahilkarim.vyapar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.rahilkarim.vyapar.home.HomeActivity
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.GlobalClass
import com.rahilkarim.vyapar.databinding.FragmentVerifyOTPBinding

class VerifyOTPFragment : Fragment() {

    var TAG = "VerifyOTPFragment"
    private lateinit var activity: Context

    lateinit var globalClass: GlobalClass
    lateinit var binding: FragmentVerifyOTPBinding

    lateinit var toolbar: androidx.appcompat.widget.Toolbar;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = requireContext()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerifyOTPBinding.inflate(layoutInflater, container, false)

        init()
        setText()
        onClick()
        setToolbar()

        return binding.root
    }

    fun init() {
        globalClass = (requireActivity().application as Application).globalClass
    }

    @SuppressWarnings("deprecation")
    fun setText() {

        binding.changeMobileNumbertv.text =
            globalClass.fromHtml(context!!.resources.getString(
                R.string.changeMobileNumber,"Change"))
    }

    fun onClick() {

        binding.changeMobileNumbertv.setOnClickListener {

            requireActivity().onBackPressed()
        }

        binding.otp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                binding.submitbt.isEnabled = s != null && s.length == 6
            }
        })

        binding.submitbt.setOnClickListener {

            val intent = Intent(requireActivity(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    fun setToolbar() {
        toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = ""
        toolbar.setNavigationOnClickListener {
            getActivity()?.onBackPressed()
        }
    }
}