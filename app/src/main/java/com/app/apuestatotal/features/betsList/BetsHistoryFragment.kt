package com.app.apuestatotal.features.betsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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
    private lateinit var filterSpinner: Spinner

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFilterView()
    }

    private fun initFilterView() {
        filterSpinner = requireView().findViewById(R.id.spinnerFilter)

        val filterOptions = arrayOf("Todos", Bet.WON_STATUS, Bet.LOST_STATUS, Bet.OPEN_STATUS)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, filterOptions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        filterSpinner.adapter = spinnerAdapter

        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = filterOptions[position]
                filterBets(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //Nothing to do
            }
        }
    }

    private fun filterBets(selectedFilter: String) {
        betsListViewModel.bets.value?.let { bets ->
            val filteredList = when (selectedFilter) {
                Bet.WON_STATUS -> bets.filter { it.status == Bet.WON_STATUS }
                Bet.LOST_STATUS -> bets.filter { it.status == Bet.LOST_STATUS }
                Bet.OPEN_STATUS -> bets.filter { it.status == Bet.OPEN_STATUS }
                else -> bets
            }
            adapter.betList = filteredList
            adapter.notifyDataSetChanged()
        }
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


