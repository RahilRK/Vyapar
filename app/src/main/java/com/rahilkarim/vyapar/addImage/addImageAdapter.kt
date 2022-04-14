package com.rahilkarim.skpust.BusinessDetailFrag

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rahilkarim.vyapar.addImage.addImageModel
import com.rahilkarim.vyapar.databinding.AddimagesItemBinding
import com.rahilkarim.vyapar.databinding.PickimagesItemBinding
import com.rahilkarim.vyapar.util.Constant
import com.rahilkarim.vyapar.util.GlobalClass

class addImageAdapter(
    private val activity: Context,
    private val arrayList: ArrayList<addImageModel>,
    private val addImageAdpOnClick: addImageAdapterOnClick,
    private val pickImage: addImageAdapter_PickImageOnClick,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var globalClass: GlobalClass = GlobalClass.getInstance(activity)
    var tag = "addImageAdapter"

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item

        if (viewType == TYPE_IMAGES) {
            val binding =
                AddimagesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MyItemHolder(binding)
        } else if (viewType == TYPE_PICKIMAGE) {
            val binding =
                PickimagesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PickImageHolder(binding)
        }
        throw RuntimeException("There is no type that matches the type $viewType. Make sure you are using view types correctly!")
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            TYPE_IMAGES -> (holder as MyItemHolder).bind(arrayList[position], position)
            TYPE_PICKIMAGE -> (holder as PickImageHolder).bind()
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {

//        if(arrayList.size < Constant.maxItemImage) {
//        }
//        else if(arrayList.size >= Constant.maxItemImage) {
//            return Constant.maxItemImage
//        }
//
//        return 0
        return arrayList.size+1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == arrayList.size) {
            TYPE_PICKIMAGE
        } else {
            TYPE_IMAGES
        }
    }

    inner class MyItemHolder(val binding: AddimagesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: addImageModel, position: Int) {
            val requestOptions: RequestOptions = RequestOptions
                .bitmapTransform(RoundedCorners(8))
            Glide.with(activity)
                .load(model.imageUri)
                .apply(requestOptions)
                .into(binding.ivImage)

            binding.ivRemoveImage.setOnClickListener {
                addImageAdpOnClick.removeImage(position,model)
            }
        }
    }

    inner class PickImageHolder(val binding: PickimagesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            if(arrayList.size >= Constant.maxItemImage) {
                binding.pickMoreImageLayout.visibility = View.GONE
            }
            else {
                binding.pickMoreImageLayout.visibility = View.VISIBLE
            }
            binding.pickMoreImageLayout.setOnClickListener {
                pickImage.addImage()
            }
        }
    }

    fun removeItem(position: Int) {
        arrayList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, arrayList.size)
    }

    interface addImageAdapterOnClick {

        fun removeImage(pos: Int, listModel: addImageModel)
    }

    interface addImageAdapter_PickImageOnClick {

        fun addImage()
    }

    companion object {
        private const val TYPE_IMAGES = 1
        private const val TYPE_PICKIMAGE = 0
    }
}