package com.example.fragmentnavigationintro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fragmentnavigationintro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Initialize the binding object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //The dataBinding way
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}