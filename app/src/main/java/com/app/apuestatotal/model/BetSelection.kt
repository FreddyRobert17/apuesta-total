package com.app.apuestatotal.model

import com.google.gson.annotations.SerializedName

data class BetSelection(
    @SerializedName("SelectionId") val selectionId: Long?,
    @SerializedName("SelectionStatus") val selectionStatus: Int?,
    @SerializedName("Price") val price: String?,
    @SerializedName("Name") val name: String?,
    @SerializedName("Spec") val spec: String?,
    @SerializedName("MarketTypeId") val marketTypeId: Int?,
    @SerializedName("MarketId") val marketId: Long?,
    @SerializedName("MarketName") val marketName: String?,
    @SerializedName("IsLive") val isLive: Boolean?,
    @SerializedName("IsBetBuilder") val isBetBuilder: Boolean?,
    @SerializedName("IsBanker") val isBanker: Boolean?,
    @SerializedName("IsVirtual") val isVirtual: Boolean?,
    @SerializedName("EventId") val eventId: Long?,
    @SerializedName("EventName") val eventName: String?,
    @SerializedName("SportTypeId") val sportTypeId: Int?,
    @SerializedName("CategoryId") val categoryId: Int?,
    @SerializedName("ChampId") val champId: Int?,
    @SerializedName("EventDate") val eventDate: String?,
    @SerializedName("EventScore") val eventScore: String?,
    @SerializedName("GameTime") val gameTime: String?
)