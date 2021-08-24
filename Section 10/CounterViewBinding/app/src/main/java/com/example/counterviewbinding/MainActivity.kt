package com.example.counterviewbinding

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.counterviewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Initialize the binding object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //The old way
        //setContentView(R.layout.activity_main)

        //View Binding!
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set click listeners
        binding.mainActivityBtSubmit.setOnClickListener { submitNumber() }
        binding.mainActivityBtRandomNumber.setOnClickListener { generateRandomNumber() }
        binding.mainActivityBtIncrement.setOnClickListener { changeNumber("+") }
        binding.mainActivityBtDecrement.setOnClickListener { changeNumber("-") }

        //Check for bundle
        if (savedInstanceState != null){
            binding.mainActivityTvNumber.text = savedInstanceState.getString("myNumber")
            binding.mainActivityEtInterval.setText(savedInstanceState.getString("myInterval"))
            binding.mainActivityTvSummary.text = savedInstanceState.getString("mySummary")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("myNumber", binding.mainActivityTvNumber.text.toString())
        outState.putString("myInterval", binding.mainActivityEtInterval.text.toString())
        outState.putString("mySummary", binding.mainActivityTvSummary.text.toString())
    }

    private fun submitNumber(){
        var startingNumber = binding.mainActivityEtNumberInput.text.toString()
        if (startingNumber == ""){
            startingNumber = "10"
        }

        binding.mainActivityTvNumber.text = startingNumber
        binding.mainActivityEtNumberInput.setText("")
        hideKeyboard()
    }

    private fun generateRandomNumber(){
        val randomNumber = (-100..100).random()
        binding.mainActivityTvNumber.text = randomNumber.toString()
    }

    private fun changeNumber(operation: String){
        val currentNumber = binding.mainActivityTvNumber.text.toString().toInt()
        var incrementValue = binding.mainActivityEtInterval.text.toString()

        if (incrementValue == ""){
            incrementValue = "1"
        }

        if (operation == "+"){
            val newNumber = currentNumber + incrementValue.toInt()
            binding.mainActivityTvNumber.text = newNumber.toString()
            binding.mainActivityTvSummary.text = "$currentNumber + $incrementValue = $newNumber"
        } else {
            val newNumber = currentNumber - incrementValue.toInt()
            binding.mainActivityTvNumber.text = newNumber.toString()
            binding.mainActivityTvSummary.text = "$currentNumber - $incrementValue = $newNumber"
        }
    }

    private fun hideKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.mainActivityTvNumber.windowToken, 0)
    }
}