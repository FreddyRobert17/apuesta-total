package com.app.apuestatotal.model

data class Bet(
    val db: Int,
    val operation: Long,
    val game: String,
    val created_date: String,
    val status: String,
    val wager: Int,
    val winning: Any?,
    val odds: Double,
    val type: String,
    val account: String
){
    companion object{
        const val GAME_NUMBER = "game_number"
    }
}