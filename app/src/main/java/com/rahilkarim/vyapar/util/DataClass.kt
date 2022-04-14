package com.rahilkarim.vyapar.util

import com.rahilkarim.vyapar.home.dashboard.monthSummaryModel
import com.rahilkarim.vyapar.R
import com.rahilkarim.vyapar.home.dashboardTab.itemsTab.itemModel
import com.rahilkarim.vyapar.home.dashboardTab.partiesTab.partiesListModel
import com.rahilkarim.vyapar.home.dashboardTab.transactionsTab.transactionListModel
import com.rahilkarim.vyapar.itemDetail.itemTransactionsModel

public class DataClass {

    companion object {

        fun getMonthSummary(): ArrayList<monthSummaryModel> {

            val array = arrayListOf<monthSummaryModel>()
            array.add(
                monthSummaryModel(
                    R.drawable.ic_arrow_back,
                    R.color.successColor,
                    270,
                    "You'll Get",
                    78000.00
                )
            )
            array.add(
                monthSummaryModel(
                    R.drawable.ic_sales,
                    R.color.orange,
                    0,
                    "Sale (Dec)",
                    95000.00
                )
            )
            array.add(
                monthSummaryModel(
                    R.drawable.ic_arrow_back,
                    R.color.warningColor,
                    90,
                    "You'll Give",
                    1600.00
                )
            )
            array.add(
                monthSummaryModel(
                    R.drawable.ic_purchase,
                    R.color.blue,
                    0,
                    "Purchase (Dec)",
                    37800.00
                )
            )

            return array
        }

        fun getParties(): ArrayList<partiesListModel> {

            val array = arrayListOf<partiesListModel>()
            array.add(
                partiesListModel(
                    "Diamond Clothing Collection",
                    23000.00,
                    "18 Nov 2021",
                    true
                )
            )
            array.add(
                partiesListModel(
                    "Bhatia Store",
                    5000.00,
                    "01 Dec 2021",
                    false
                )
            )
            array.add(
                partiesListModel(
                    "Famous Fast Food",
                    12000.00,
                    "13 Nov 2021",
                    true
                )
            )

            return array
        }

        fun getTransactions(): ArrayList<transactionListModel> {

            val array = arrayListOf<transactionListModel>()
            array.add(
                transactionListModel(
                    "Diamond Clothing Collection",
                    "Sale",
                    "18 Nov 2021",
                    "10:10 PM",
                    23000.00,
                    20000.00
                )
            )

            array.add(
                transactionListModel(
                    "Bhatia Store",
                    "Purchase",
                    "01 Dec 2021",
                    "02:23 PM",
                    5000.00,
                    0.00
                )
            )

            array.add(
                transactionListModel(
                    "Famous Fast Food",
                    "Purchase",
                    "13 Nov 2021",
                    "03:15 PM",
                    5000.00,
                    0.00
                )
            )

            return array
        }

        fun getItems(): ArrayList<itemModel> {

            val array = arrayListOf<itemModel>()
            array.add(
                itemModel(
                    "Google Pixel 6",
                    60000.00,
                    50000.00,
                    4.0
                )
            )
            array.add(
                itemModel(
                    "iPhone 13 Pro",
                    90000.00,
                    85000.00,
                    10.0
                )
            )
            array.add(
                itemModel(
                    "OnePlus Nord",
                    30000.00,
                    25000.00,
                    7.0
                )
            )

            return array
        }

        fun getItemTransactions(): ArrayList<itemTransactionsModel> {

            val array = arrayListOf<itemTransactionsModel>()
            array.add(
                itemTransactionsModel(
                    "Purchase",
                    "18 Nov 2021",
                    4.00,
                    20000.00
                )
            )

            array.add(
                itemTransactionsModel(
                    "Sale",
                    "05 Nov 2021",
                    2.00,
                    12000.00
                )
            )

            array.add(
                itemTransactionsModel(
                    "Purchase",
                    "01 Oct 2021",
                    30.00,
                    50000.00
                )
            )

            return array
        }
    }
}