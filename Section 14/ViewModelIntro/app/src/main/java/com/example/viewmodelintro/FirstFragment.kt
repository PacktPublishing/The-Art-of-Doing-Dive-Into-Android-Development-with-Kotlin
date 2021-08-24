package com.example.viewmodelintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.viewmodelintro.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        //Set the value of the view model
        //This is the bad way...we keep getting new view models and they don't get destroyed
        //viewModel = FirstViewModel()

        //This is the correct way, using a ViewModelProvider which will return a viewModel if already made
        //Otherwise it will make for you.
        viewModel = ViewModelProvider(this).get(FirstViewModel::class.java)

        //Using dataBinding to bind data directly to the views in XML.
        binding.firstViewModel = viewModel
        binding.lifecycleOwner = this

        //Not using dataBinding, we have to create observers to observe the live data
        //viewModel.message.observe(viewLifecycleOwner, Observer { myMessage -> binding.firstFragmentTvMessage.text = myMessage})
        //viewModel.clicks.observe(viewLifecycleOwner, Observer { myClicks ->binding.firstFragmentTvClicks.text = "$myClicks total clicks" })

        //Set click listeners
        binding.firstFragmentBtHello.setOnClickListener { viewModel.hello(binding.firstFragmentEtName.text.toString()) }
        binding.firstFragmentBtGoodbye.setOnClickListener { viewModel.goodbye(binding.firstFragmentEtName.text.toString()) }
        binding.firstFragmentBtEnd.setOnClickListener { nextFragment() }

        //updateUI()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun nextFragment(){
        findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(viewModel.clicks.value!!))
    }
    //These are the old versions not using the view model
    /*private fun hello(){
        val name = binding.firstFragmentEtName.text.toString()
        binding.firstFragmentTvMessage.text = "Hello $name!"

        clicks++
        binding.firstFragmentTvClicks.text = "$clicks total clicks"
    }

    private fun goodbye(){
        val name = binding.firstFragmentEtName.text.toString()
        binding.firstFragmentTvMessage.text = "Goodbye $name!"

        clicks++
        binding.firstFragmentTvClicks.text = "$clicks total clicks"
    }*/

    //The old way not using Live Data
    /*private fun hello(){
        val name = binding.firstFragmentEtName.text.toString()
        viewModel.message = "Hello $name!"
        viewModel.clicks++
        updateUI()
    }

    private fun goodbye(){
        val name = binding.firstFragmentEtName.text.toString()
        viewModel.message = "Goodbye $name!"
        viewModel.clicks++
        updateUI()
    }

    private fun updateUI(){
        binding.firstFragmentTvMessage.text = viewModel.message
        binding.firstFragmentTvClicks.text = "${viewModel.clicks} total clicks"
    }*/
}