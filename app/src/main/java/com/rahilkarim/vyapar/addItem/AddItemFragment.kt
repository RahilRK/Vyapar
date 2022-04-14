package com.rahilkarim.vyapar.addItem

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.databinding.FragmentAddItemBinding
import com.rahilkarim.vyapar.databinding.FragmentVerifyOTPBinding
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.GlobalClass

class AddItemFragment : Fragment() {

    var TAG = "AddItemFragment"
    private lateinit var activity: Context

    lateinit var globalClass: GlobalClass
    lateinit var binding: FragmentAddItemBinding

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
        binding = FragmentAddItemBinding.inflate(layoutInflater, container, false)

        init()
        setToolbar()
        onClick()
        fillItemUnit()

        return binding.root
    }

    fun init() {
        globalClass = (requireActivity().application as Application).globalClass
    }

    fun setToolbar() {
        toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            getActivity()?.onBackPressed()
        }
    }

    fun onClick() {
        binding.addImageLayout.setOnClickListener {

            requestStoragePermission()
        }
    }

    fun requestStoragePermission() {

        Dexter.withActivity(requireActivity())
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        globalClass.log(TAG, "Storage permission Granted")
                        findNavController().navigate(R.id.action_addItemFragment_to_addImageFragment)
                    }
                    if (report.deniedPermissionResponses.size > 0) {
                        globalClass.log(TAG, "Storage permission Denied")
                        globalClass.toastlong("Storage permission required to choose image")
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            })
            .withErrorListener { error ->
                globalClass.log(TAG + "__requestStoragePermission", error.toString())
            }
            .onSameThread()
            .check()
    }

    fun fillItemUnit() {
        val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(requireContext(), R.layout.unitlist_item, items)
        (binding.itemUnit as? AutoCompleteTextView)?.setAdapter(adapter)
    }
}