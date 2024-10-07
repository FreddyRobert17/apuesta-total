package com.app.apuestatotal.features.betsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.apuestatotal.model.Bet
import com.app.apuestatotal.repository.BetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BetsListViewModel(private val repository: BetRepository) : ViewModel() {

    private val _bets = MutableLiveData<List<Bet>>()
    val bets: LiveData<List<Bet>>
        get() = _bets

    private val _filteredBets = MutableLiveData<List<Bet>>()
    val filteredBets: LiveData<List<Bet>>
        get() = _filteredBets

    fun fetchBets() {
        viewModelScope.launch(Dispatchers.IO) {
            val betList = repository.getBets()
            _bets.postValue(betList)
        }
    }

    fun filterBets(status: String = "approved") {
        val currentBets = _bets.value ?: emptyList()
        val filteredList = currentBets.filter { it.status != status }
        _filteredBets.postValue(filteredList)
    }
}