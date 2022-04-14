package com.rahilkarim.vyapar.addImage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rahilkarim.skpust.BusinessDetailFrag.addImageAdapter
import com.rahilkarim.skpust.BusinessDetailFrag.monthSummaryAdapter
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.databinding.FragmentAddImageBinding
import com.rahilkarim.vyapar.databinding.FragmentAddItemBinding
import com.rahilkarim.vyapar.home.dashboard.monthSummaryModel
import com.rahilkarim.vyapar.util.Application
import com.rahilkarim.vyapar.util.Constant
import com.rahilkarim.vyapar.util.GlobalClass
import droidninja.filepicker.FilePickerBuilder
import droidninja.filepicker.FilePickerConst
import java.util.ArrayList


class AddImageFragment : Fragment(),addImageAdapter.addImageAdapterOnClick,addImageAdapter.addImageAdapter_PickImageOnClick {

    var TAG = "AddItemFragment"
    private lateinit var activity: Context

    lateinit var globalClass: GlobalClass
    lateinit var binding: FragmentAddImageBinding

    lateinit var toolbar: androidx.appcompat.widget.Toolbar;

    var selectedimagesArrayList = ArrayList<Uri>()

    var arrayList = arrayListOf<addImageModel>()
    lateinit var adapter: addImageAdapter;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = requireContext()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddImageBinding.inflate(layoutInflater, container, false)

        init()
        setToolbar()
        onClick()

        return binding.root
    }

    fun init() {
        globalClass = (requireActivity().application as Application).globalClass
    }

    fun setToolbar() {
        toolbar = binding.toolbar
        toolbar.title = resources.getString(R.string.add_image)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            getActivity()?.onBackPressed()
        }
    }

    fun onClick() {

        binding.pickImageLayout.setOnClickListener {

            openImagePicker()
        }
    }

    fun openImagePicker() {
        selectedimagesArrayList.clear()
        FilePickerBuilder.instance
            .setMaxCount(Constant.maxItemImage)
            .enableCameraSupport(false)
            .setSelectedFiles(selectedimagesArrayList)
            .setActivityTheme(R.style.LibAppTheme)
            .pickPhoto(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FilePickerConst.REQUEST_CODE_PHOTO) {
            if (resultCode == Activity.RESULT_OK && data != null) {
//                selectedimagesArrayList.clear()
                data.getParcelableArrayListExtra<Uri>(FilePickerConst.KEY_SELECTED_MEDIA)
                    ?.let { selectedimagesArrayList.addAll(it) }

                globalClass.log(TAG, "selectedImages: ${selectedimagesArrayList.size}")
                if(selectedimagesArrayList.isNotEmpty()) {

                    for(i in 0 until selectedimagesArrayList.size) {
                        arrayList.add(addImageModel(selectedimagesArrayList.get(i)))
                    }

                    showHideRecyclerView()
                    setAdapter(arrayList)
                }
                else {
                    globalClass.toastlong("Unable to load image")
                }
            }
        }
    }

    fun setAdapter(arrayList: ArrayList<addImageModel>) {

        try {
//            globalClass.log(TAG, "setAdapter selectedImages: ${arrayList.size}")
            val recyclerview = binding.recyclerView
            recyclerview.layoutManager = GridLayoutManager(context,3)
            adapter = addImageAdapter(requireActivity(), arrayList,this,this)
            recyclerview.adapter = adapter
        }
        catch (e: Exception) {

            val error = Log.getStackTraceString(e)
            globalClass.log(TAG,error)
        }
    }

    fun showHideRecyclerView() {
        if(arrayList.isEmpty()) {
            binding.recyclerView.visibility = View.GONE
            binding.pickImageLayout.visibility = View.VISIBLE
            binding.tapPickImageLabel.visibility = View.VISIBLE
        }
        else {
            binding.pickImageLayout.visibility = View.GONE
            binding.tapPickImageLabel.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    override fun removeImage(pos: Int, listModel: addImageModel) {

        adapter.removeItem(pos)
        showHideRecyclerView()
    }

    override fun addImage() {
        openImagePicker()
    }
}