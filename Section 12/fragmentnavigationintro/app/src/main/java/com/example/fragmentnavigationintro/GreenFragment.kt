package com.example.fragmentnavigationintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentnavigationintro.databinding.FragmentGreenBinding

class GreenFragment : Fragment() {
    //Initialize the binding object
    private lateinit var binding: FragmentGreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_green, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_green, container, false)

        //Get passed data from safe args
        val args = GreenFragmentArgs.fromBundle(requireArguments())
        binding.greenFragmentTvPassedMessages.text = "${args.currentMessage}\n${args.passedMessages}"

        //set click listeners (old way)
        //binding.greenFragmentBtNext.setOnClickListener { findNavController().navigate(R.id.action_greenFragment_to_blueFragment) }

        //Safe args
        binding.greenFragmentBtNext.setOnClickListener { findNavController().
            navigate(GreenFragmentDirections.
                actionGreenFragmentToBlueFragment(binding.greenFragmentEtCurrentMessage.text.toString(),
                binding.greenFragmentTvPassedMessages.text.toString())) }

        return binding.root
    }
}