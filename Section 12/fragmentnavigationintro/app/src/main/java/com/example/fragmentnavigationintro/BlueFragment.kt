package com.example.fragmentnavigationintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentnavigationintro.databinding.FragmentBlueBinding

class BlueFragment : Fragment() {
    //Initialize binding object
    private lateinit var binding: FragmentBlueBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment (the old way)
        //return inflater.inflate(R.layout.fragment_blue, container, false)

        //Data binding way
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blue, container, false )

        //Get passed data using safe args
        val args = BlueFragmentArgs.fromBundle(requireArguments())
        binding.blueFragmentTvPassedMessages.text = "${args.currentMessage}\n${args.passedMessages}"

        //Set click listener (old way)
        //binding.blueFragmentBtNext.setOnClickListener { findNavController().navigate(R.id.action_blueFragment_to_redFragment) }

        //Safe args
        binding.blueFragmentBtNext.setOnClickListener { findNavController().
            navigate(BlueFragmentDirections.
            actionBlueFragmentToRedFragment(binding.blueFragmentEtCurrentMessage.text.toString(),
            binding.blueFragmentTvPassedMessages.text.toString())) }

        return binding.root
    }
}