package com.rahilkarim.vyapar.itemDetail

data class itemTransactionsModel(
    val transactionType: String,
    val transactionDate: String,
    val quantity: Double,
    val totalAmount: Double,
)
