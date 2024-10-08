package com.app.apuestatotal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.apuestatotal.R
import com.app.apuestatotal.model.Bet

class BetAdapter(var betList: List<Bet>, private val listener: (Bet) -> Unit) : RecyclerView.Adapter<BetAdapter.BetViewHolder>() {

    class BetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val betGameNumber: TextView = itemView.findViewById(R.id.betGameNumber)
        val betCreatedDate: TextView = itemView.findViewById(R.id.betCreatedDate)
        val betStatus: TextView = itemView.findViewById(R.id.betStatus)
        val betWager: TextView = itemView.findViewById(R.id.betWager)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bet_item, parent, false)
        return BetViewHolder(view)
    }

    override fun onBindViewHolder(holder: BetViewHolder, position: Int) {
        val bet = betList[position]
        holder.betGameNumber.text = "N°: ${bet.game}"
        holder.betCreatedDate.text = "Fecha de creación: ${bet.created_date}"
        holder.betStatus.text = "Estado: ${bet.status}"
        holder.betWager.text = "Apuesta: ${bet.wager.toString()}"

        holder.itemView.setOnClickListener {
            listener.invoke(bet)
        }
    }

    override fun getItemCount(): Int = betList.size
}
