package com.app.apuestatotal.repository

import android.content.Context
import com.app.apuestatotal.model.BetDetail
import com.app.apuestatotal.utils.loadJSONFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BetDetailRepository(private val context: Context) {

    fun getBetDetailById(betId: Long): BetDetail? {
        val betsJson = loadJSONFromAsset(context, "betsDetailHistory.json")

        if(betsJson.isNullOrEmpty()) return null

        val bets: List<BetDetail> = parseJson(betsJson)

        return bets.find { it.betId == betId }
    }

    private fun parseJson(json: String): List<BetDetail> {
        val gson = Gson()
        val type = object : TypeToken<List<BetDetail>>() {}.type
        return gson.fromJson(json, type)
    }
}