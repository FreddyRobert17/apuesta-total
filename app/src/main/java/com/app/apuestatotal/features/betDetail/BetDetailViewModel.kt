package com.app.apuestatotal.features.betDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.apuestatotal.model.Bet
import com.app.apuestatotal.model.BetDetail
import com.app.apuestatotal.repository.BetDetailRepository
import com.app.apuestatotal.repository.BetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BetDetailViewModel(private val repository: BetDetailRepository) : ViewModel() {

    private val _betDetail = MutableLiveData<BetDetail?>()
    val betDetail: LiveData<BetDetail?> get() = _betDetail

    fun getBetDetailById(betId: Long) {
        val result = repository.getBetDetailById(betId)
        _betDetail.postValue(result)
    }
}