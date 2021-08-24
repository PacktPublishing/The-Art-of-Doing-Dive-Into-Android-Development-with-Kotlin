package com.example.click

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(private val score: Int): ViewModel() {
    //Set up live data
    private val _finalScore = MutableLiveData<Int>()
    val finalScore: LiveData<Int> get() = _finalScore

    private val _hasGambled = MutableLiveData<Boolean>()
    val hasGambled: LiveData<Boolean> get() = _hasGambled

    //Set the value of live data
    init{
        _finalScore.value = score
    }

    //User wants to gamble their points
    fun gamble(){
        val swing = (-5..5).random()
        _finalScore.value = _finalScore.value?.plus(swing)
        _hasGambled.value = true
    }
}