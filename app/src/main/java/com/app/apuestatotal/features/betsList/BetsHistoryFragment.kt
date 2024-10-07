package com.app.apuestatotal.features.betsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.apuestatotal.R
import com.app.apuestatotal.adapters.BetAdapter
import com.app.apuestatotal.features.betDetail.BetDetailFragment
import com.app.apuestatotal.model.Bet
import com.app.apuestatotal.repository.BetRepository

class BetsHistoryFragment : Fragment() {

    private lateinit var betsListViewModel: BetsListViewModel
    private lateinit var adapter: BetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bets_history, container, false)

        val repository = BetRepository(requireActivity())

        val factory = BetsListViewModelFactory(repository)
        betsListViewModel = ViewModelProvider(this, factory).get(BetsListViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewBets)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = BetAdapter(emptyList()){
            navigateToBetDetailFragment(it)
        }

        recyclerView.adapter = adapter

        betsListViewModel.bets.observe(viewLifecycleOwner) { betList ->
            adapter.betList = betList
            adapter.notifyDataSetChanged()
        }

        betsListViewModel.fetchBets()

        return view
    }

    private fun navigateToBetDetailFragment(bet: Bet) {
        val betDetailFragment = BetDetailFragment()

        val bundle = Bundle()
        bundle.putString(Bet.GAME_NUMBER, bet.game)
        betDetailFragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, betDetailFragment)
            .addToBackStack(null)
            .commit()
    }
}


