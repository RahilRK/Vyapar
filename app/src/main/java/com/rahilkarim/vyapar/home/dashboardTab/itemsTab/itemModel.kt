package com.rahilkarim.vyapar.home.dashboardTab.itemsTab

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class itemModel(
    val itemName:String,
    val salesPrice:Double,
    val purchasePrice:Double,
    val inStock:Double,
):Parcelable
