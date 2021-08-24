package com.example.diapertracker

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //Declare var's but set the value later
    private lateinit var dirtyButton:  RadioButton
    private lateinit var wetButton: RadioButton
    private lateinit var dryButton: RadioButton
    private lateinit var currentTime: EditText
    private lateinit var diaperChangesText: TextView
    private lateinit var diaperChangesCount: TextView

    //Counter variable
    private var diaperCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get a reference to our buttons and set listeners
        val addButton: Button = findViewById(R.id.main_activity_bt_add)
        val clearButton: Button = findViewById(R.id.main_activity_bt_clear)

        addButton.setOnClickListener { addNewDiaper() }
        clearButton.setOnClickListener { clear() }

        //Set values to other views
        dirtyButton = findViewById(R.id.main_activity_rb_dirty)
        wetButton = findViewById(R.id.main_activity_rb_wet)
        dryButton = findViewById(R.id.main_activity_rb_dry)
        currentTime = findViewById(R.id.main_activity_et_time)
        diaperChangesText = findViewById(R.id.main_activity_tv_diaper_changes)
        diaperChangesCount = findViewById(R.id.main_activity_tv_diaper_count)
    }

    //Create a new diaper to add to a list
    private fun addNewDiaper(){
        //Get the current time
        var timeOfChange = "00:00"
        if (currentTime.text.toString() != "") {
            timeOfChange = currentTime.text.toString()
        }

        var newDiaper = ""
        newDiaper = when {
            dirtyButton.isChecked -> {
                //Log.i("test", "Dirty Diaper Changed")
                "- A dirty diaper was changed at $timeOfChange"
            }
            wetButton.isChecked -> {
                //Log.i("test", "Wet diaper changed")
                "- A wet diaper was changed at $timeOfChange"
            }
            else -> {
                //Log.i("test", "Dry diaper changed")
                "- A dry diaper was changed at $timeOfChange"
            }
        }

        diaperCount++

        //Update our diaper list
        updateDiaperList(newDiaper)
    }

    //Add the new diaper to our list
    private fun updateDiaperList(newDiaper: String){
        //Get the old list of diapers and add the new one to the end
        val oldDiapers = diaperChangesText.text.toString()
        val updatedDiapers = "$oldDiapers \n$newDiaper"

        //Update UI text
        diaperChangesText.text = updatedDiapers
        diaperChangesCount.text = "$diaperCount total diapers changed."

        //Clear the editText
        currentTime.setText("")

        //Hide the keyboard
        hideKeyboard()
    }

    //Clear all diapers from our list
    private fun clear(){
        //Reset all UI Text and counters
        diaperChangesText.text = ""
        diaperChangesCount.text = ""
        diaperCount = 0
    }

    //Hide keyboard
    private fun hideKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentTime.windowToken, 0)
    }
}