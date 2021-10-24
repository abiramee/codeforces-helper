package com.abiramee.codeforceshelper.domain.model

data class Status(
    val monthlyCount: Int,
    val dailyCount: Int,
    val acceptedList: List<AcceptedItem>
)