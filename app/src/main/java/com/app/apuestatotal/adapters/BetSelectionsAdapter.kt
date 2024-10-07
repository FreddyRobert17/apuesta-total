package com.app.apuestatotal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.apuestatotal.R
import com.app.apuestatotal.model.BetSelection

class BetSelectionsAdapter(private val selections: List<BetSelection>) : RecyclerView.Adapter<BetSelectionsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val selectionName: TextView = view.findViewById(R.id.tvSelectionName)
        val selectionDetails: TextView = view.findViewById(R.id.tvSelectionDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bet_selection, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selection = selections[position]
        holder.selectionName.text = selection.name
        holder.selectionDetails.text = selection.eventName
    }

    override fun getItemCount(): Int {
        return selections.size
    }
}
