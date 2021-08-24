package com.example.motivateme

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //Declare our references to views before layout inflation
    private lateinit var name: EditText
    private lateinit var message: TextView

    //Override lifecycle method onCreate.  This should initialize all important items for the code.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflate the given layout to turn xml views into kotlin objects.
        setContentView(R.layout.activity_main)

        //Get a reference to the button view from our layout and set a clickListener
        val updateButton: Button = findViewById(R.id.activity_main_bt_update)
        updateButton.setOnClickListener { updateMessage() }

        //Set the value to our declared views
        name = findViewById(R.id.activity_main_et_name)
        message = findViewById(R.id.main_activity_tv_message)

        //Check if a bundle is present, if it's not savedInstanceState will be null.
        if (savedInstanceState != null){
            message.text = savedInstanceState.getString("myMessage")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Put some data in our bundle
        outState.putString("myMessage", message.text.toString())
    }

    //Get a name from an editText and display a personalized message
    private fun updateMessage(){
        //Get the username from the editText
        val username = name.text

        //Pick a motivational message
        val motivationalMessages = listOf("Keep working hard!", "Never give up!", "Keep your head up!", "Difficultly is growth!", "Today is the first day of the rest of your life!")
        val index = (0..4).random()
        val currentMessage = motivationalMessages[index]

        //Update the textView to display our message
        if (username.toString() == ""){
            message.text = "Make sure to enter your name!"
        } else {
            message.text = "Hello $username!  $currentMessage"
        }

        //Clear the editText and use setText because editText's require an editable.
        name.setText("")

        //Hide the keyboard
        hideKeyboard()
    }

    //Hide the keyboard using an InputMethodManager
    private fun hideKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(name.windowToken, 0)
    }

}