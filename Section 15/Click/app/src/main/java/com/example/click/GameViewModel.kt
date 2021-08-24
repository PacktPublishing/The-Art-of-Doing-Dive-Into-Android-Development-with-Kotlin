package com.example.click

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private val timer: CountDownTimer

    //Set up live data
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long> get() = _currentTime

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private val _currentButton = MutableLiveData<Int>()
    val currentButton: LiveData<Int> get() = _currentButton

    private val _scoreColor = MutableLiveData<String>()
    val scoreColor: LiveData<String> get() = _scoreColor

    private val _gameFinished = MutableLiveData<Boolean>()
    val gameFinished: LiveData<Boolean> get() = _gameFinished

    //Set the live data values and set up and start the timer
    init{
        _score.value = 0
        _currentButton.value = (1..4).random()
        _scoreColor.value = "purple"
        _gameFinished.value = false

        //Set up the timer
        timer = object: CountDownTimer(31000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / 1000
                _scoreColor.value = "purple"
            }

            override fun onFinish() {
                _gameFinished.value = true
            }
        }

        //Start the timer
        timer.start()
    }

    //Gain a point because you clicked a button
    fun gainPoint(){
        _score.value = _score.value?.plus(1)
        _currentButton.value = (1..4).random()
        _scoreColor.value = "purple"
    }

    //Lose a point because you missed a button
    fun losePoint(){
        _score.value = _score.value?.plus(-1)
        _currentButton.value = (1..4).random()
        _scoreColor.value = "red"
    }
}