package com.app.apuestatotal.repository

import android.content.Context
import com.app.apuestatotal.model.Bet
import com.app.apuestatotal.utils.loadJSONFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class BetRepository(private val context: Context) {

    fun getBets(): List<Bet> {
        val betsJson = loadJSONFromAsset(context, "bets.json")

        if (betsJson != null) {
            val gson = Gson()
            val betListType = object : TypeToken<List<Bet>>() {}.type
            return gson.fromJson(betsJson, betListType)
        }

        return emptyList()
    }
}