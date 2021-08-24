package com.example.viewmodelintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.viewmodelintro.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: SecondViewModel
    private lateinit var viewModelFactory: SecondViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false )
        viewModelFactory = SecondViewModelFactory(SecondFragmentArgs.fromBundle(requireArguments()).clicks)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SecondViewModel::class.java)

        //Set up observer for live data
        viewModel.clicks.observe(viewLifecycleOwner, Observer { clickNumber -> binding.secondFragmentTvClicks.text = clickNumber.toString() })

        //Set up click listeners
        binding.secondFragmentBtClick.setOnClickListener { viewModel.addClick() }
        binding.secondFragmentBtBack.setOnClickListener { findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment()) }

        // Inflate the layout for this fragment
        return binding.root
    }
}