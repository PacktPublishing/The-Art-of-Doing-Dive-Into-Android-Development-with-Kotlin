package com.example.helloworld

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

//MainActivity extends from AppCompatActivity giving it more functionality.
class MainActivity : AppCompatActivity() {
    //onCreate is a lifecycle method that runs only once at the start of the app.
    override fun onCreate(savedInstanceState: Bundle?) {
        //Extend onCreate rather than completely overriding it.
        super.onCreate(savedInstanceState)

        //Inflate our layout with setContentView and turn all views in the layout into kotlin objects
        setContentView(R.layout.activity_main)

        //Use logcat to see when lifecycle method runs
        Log.i("test", "onCreate() initiated moving to onStart()")
    }

    //Override all lifecycle methods to learn about them!
    override fun onStart(){
        super.onStart()
        Log.i("test", "onStart() initiated moving to onResume()")
    }

    override fun onResume(){
        super.onResume()
        Log.i("test", "onResume() waiting for onPause()")
    }

    override fun onPause(){
        super.onPause()
        Log.i("test", "onPause() initiated waiting for onStop() or onResume()")
    }

    override fun onStop(){
        super.onStop()
        Log.i("test", "onStop() waiting for onDestroy() or onRestart()")
    }

    override fun onRestart(){
        super.onRestart()
        Log.i("test", "onRestart() initiated moving to onStart()")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i("test", "onDestroy() initiated...the app has been killed :-(")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("test", "onSaveInstanceState called.")
    }

}