package com.app.apuestatotal.model

data class Event(
    val selectionId: Long,
    val selectionStatus: Int,
    val price: String,
    val name: String,
    val spec: String,
    val marketTypeId: Int,
    val marketId: Long,
    val marketName: String,
    val isLive: Boolean,
    val eventId: Long,
    val eventName: String,
    val sportTypeId: Int,
    val categoryId: Int,
    val eventDate: String
)
