package com.example.fragmentnavigationintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentnavigationintro.databinding.FragmentRedBinding

class RedFragment : Fragment() {
    //Initialize dataBinding object
    private lateinit var binding: FragmentRedBinding

    //Use onCreateView instead of onCreate for fragments, this method must return a view.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment (Old way)
        //return inflater.inflate(R.layout.fragment_red, container, false)

        //The dataBinding way
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_red, container, false)

        //Get passed data from bundle
        val args = RedFragmentArgs.fromBundle(requireArguments())
        binding.redFragmentTvPassedMessages.text = "${args.currentMessages}\n${args.passedMessages}"

        //Set click listener (old way)
        //binding.redFragmentBtNext.setOnClickListener { findNavController().navigate(R.id.action_redFragment_to_greenFragment) }

        //Using safe args to pass data
        binding.redFragmentBtNext.setOnClickListener { findNavController().
            navigate(RedFragmentDirections.
                actionRedFragmentToGreenFragment(binding.redFragmentEtCurrentMessage.text.toString(),
                binding.redFragmentTvPassedMessages.text.toString())) }

        return binding.root



    }
}