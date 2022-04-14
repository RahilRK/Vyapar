package com.rahilkarim.vyapar.home.dashboardTab.transactionsTab

data class transactionListModel(
    val partyName: String,
    val transactionType: String,
    val transactionDate: String,
    val transactionTime: String,
    val total: Double,
    val balance: Double,
)
