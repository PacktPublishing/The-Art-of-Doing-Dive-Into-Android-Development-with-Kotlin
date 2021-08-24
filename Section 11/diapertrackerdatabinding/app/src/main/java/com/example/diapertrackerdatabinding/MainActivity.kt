package com.example.diapertrackerdatabinding

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.diapertrackerdatabinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Initialize the binding object
    private lateinit var binding: ActivityMainBinding

    //A var to use inside our layout
    var diaperHeader = "-- Diaper Tracker --"

    private var diaperCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //The dataBinding way!
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Set click listeners
        binding.mainActivityBtAdd.setOnClickListener { addNewDiaper() }
        binding.mainActivityBtClear.setOnClickListener { clear() }

        //set the value of myActivity in the binding object
        binding.myActivity = this
    }

    private fun addNewDiaper(){
        var timeOfChange = "00:00"
        if (binding.mainActivityEtTime.text.toString() != ""){
            timeOfChange = binding.mainActivityEtTime.text.toString()
        }

        var newDiaper = ""
        when {
            binding.mainActivityRbDirty.isChecked -> {
                newDiaper = "- A dirty diaper has been changed at $timeOfChange"
                diaperHeader = "Dirty diaper changed!"
            }
            binding.mainActivityRbWet.isChecked -> {
                newDiaper = "- A wet diaper has been changed at $timeOfChange"
                diaperHeader = "Wet diaper changed!"
            }
            else -> {
                newDiaper = "- A dry diaper has been changed at $timeOfChange"
                diaperHeader = "Dry diaper changed!"
            }
        }

        binding.apply{invalidateAll()}

        diaperCount++

        updateDiaperList(newDiaper)
    }

    private fun updateDiaperList(newDiaper: String){
        val oldDiapers = binding.mainActivityTvDiaperChanges.text.toString()
        val updatedDiapers = "$oldDiapers \n$newDiaper"

        binding.mainActivityTvDiaperChanges.text = updatedDiapers
        binding.mainActivityTvDiaperCount.text = "$diaperCount total diapers changed."
        binding.mainActivityEtTime.setText("")

        hideKeyboard()
    }

    private fun clear(){
        binding.mainActivityTvDiaperChanges.text = ""
        binding.mainActivityTvDiaperCount.text = ""
        diaperCount = 0

        diaperHeader = "-- Diaper Tracker --"
        binding.apply{invalidateAll()}
    }

    private fun hideKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.mainActivityEtTime.windowToken, 0)
    }
}