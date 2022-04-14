package com.rahilkarim.vyapar.home.dashboardTab.partiesTab

data class partiesListModel(
    val partyName: String,
    val amount: Double,
    val lastPaymentDate: String,
    val isGet: Boolean,
)
