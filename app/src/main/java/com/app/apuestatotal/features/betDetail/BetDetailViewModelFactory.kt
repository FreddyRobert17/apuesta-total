package com.app.apuestatotal.features.betDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.apuestatotal.repository.BetDetailRepository

class BetDetailViewModelFactory(private val repository: BetDetailRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BetDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BetDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}