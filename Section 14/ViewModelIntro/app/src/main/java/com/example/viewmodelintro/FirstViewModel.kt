package com.example.viewmodelintro

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstViewModel: ViewModel() {
    //The old way without Live Data
    //var message = ""
    //var clicks = 0

    //The new way using Live Data
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _clicks = MutableLiveData<Int>()
    val clicks: LiveData<Int> get() = _clicks

    //Override methods to get insight into view model lifecycle
    init{
        Log.i("test", "View Model Created!")
        _message.value = ""
        _clicks.value = 0
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("test", "View Model Destroyed!")
    }

    //Public function to call from our fragment
    fun hello(name: String){
        _message.value = "Hello $name!"
        _clicks.value = clicks.value?.plus(1)
    }

    fun goodbye(name: String){
        _message.value = "Goodbye $name!"
        _clicks.value = clicks.value?.plus(1)
    }
}