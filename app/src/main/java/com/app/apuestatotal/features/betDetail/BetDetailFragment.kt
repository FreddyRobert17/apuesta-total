package com.app.apuestatotal.features.betDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.apuestatotal.R
import com.app.apuestatotal.adapters.BetSelectionsAdapter
import com.app.apuestatotal.model.Bet
import com.app.apuestatotal.model.BetDetail
import com.app.apuestatotal.repository.BetDetailRepository

class BetDetailFragment : Fragment() {
    private lateinit var viewModel: BetDetailViewModel
    private lateinit var repository: BetDetailRepository
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BetSelectionsAdapter
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_bet_detail, container, false)

        val gameNumber = arguments?.getString(Bet.GAME_NUMBER) ?: "0"
        val gameNumberLong = gameNumber.toLongOrNull() ?: 0L

        repository = BetDetailRepository(requireActivity())
        viewModel = ViewModelProvider(this, BetDetailViewModelFactory(repository))
            .get(BetDetailViewModel::class.java)

        viewModel.betDetail.observe(viewLifecycleOwner) { bet ->
            if (bet != null) {
                bindData(bet)
            }
        }

        viewModel.getBetDetailById(gameNumberLong)

        return rootView
    }

    private fun bindData(betDetail: BetDetail) {
        recyclerView = rootView.findViewById(R.id.recyclerViewBetSelections)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val selections = betDetail.betSelections ?: emptyList()
        adapter = BetSelectionsAdapter(selections)
        recyclerView.adapter = adapter

        rootView.findViewById<TextView>(R.id.tvBetNivel).text = betDetail.betNivel
        rootView.findViewById<TextView>(R.id.tvBetCreatedDate).text = betDetail.createdDate
    }
}