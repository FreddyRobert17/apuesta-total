package com.app.apuestatotal.features.betsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.apuestatotal.repository.BetRepository

class BetsListViewModelFactory(private val repository: BetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BetsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BetsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
