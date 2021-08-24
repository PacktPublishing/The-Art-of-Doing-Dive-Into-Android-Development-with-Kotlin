package com.example.viewmodelintro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SecondViewModelFactory(var passedClicks: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondViewModel::class.java)){
            return SecondViewModel(passedClicks) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}