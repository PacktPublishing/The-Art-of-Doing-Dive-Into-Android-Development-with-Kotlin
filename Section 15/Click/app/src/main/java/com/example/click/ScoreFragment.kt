package com.example.click

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.click.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    private lateinit var binding: FragmentScoreBinding
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)
        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

        //Set click listeners
        binding.scoreFragmentBtGamble.setOnClickListener { viewModel.gamble() }
        binding.scoreFragmentBtPlay.setOnClickListener { findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment()) }
        binding.scoreFragmentBtTitle.setOnClickListener { findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToTitleFragment()) }

        //Set up dataBinding to bind data directly to views
        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = this

        // Inflate the layout for this fragment
        return binding.root
    }
}