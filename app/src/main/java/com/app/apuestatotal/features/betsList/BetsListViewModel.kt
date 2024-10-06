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

    fun fetchBets() {
        viewModelScope.launch(Dispatchers.IO) {
            val betList = repository.getBets()
            _bets.postValue(betList)
        }
    }
}