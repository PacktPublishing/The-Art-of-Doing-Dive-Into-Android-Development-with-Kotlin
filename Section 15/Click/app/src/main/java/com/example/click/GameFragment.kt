package com.example.click

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.click.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        //Set up observers
        viewModel.currentTime.observe(viewLifecycleOwner, Observer { time -> binding.gameFragmentTvTimer.text = DateUtils.formatElapsedTime(time) })
        viewModel.currentButton.observe(viewLifecycleOwner, Observer { button -> moveButton(button) })
        viewModel.score.observe(viewLifecycleOwner, Observer { score -> binding.gameFragmentTvScore.text = score.toString()})
        viewModel.scoreColor.observe(viewLifecycleOwner, Observer { color -> changeScoreColor(color) })
        viewModel.gameFinished.observe(viewLifecycleOwner, Observer { isFinished -> if (isFinished) gameOver() })

        //Set click listeners
        binding.gameFragmentBt1.setOnClickListener { viewModel.gainPoint() }
        binding.gameFragmentBt2.setOnClickListener { viewModel.gainPoint() }
        binding.gameFragmentBt3.setOnClickListener { viewModel.gainPoint() }
        binding.gameFragmentBt4.setOnClickListener { viewModel.gainPoint() }
        binding.gameFragmentLoGame.setOnClickListener{ viewModel.losePoint()}

        // Inflate the layout for this fragment
        return binding.root
    }

    //Only show a given button
    private fun moveButton(button: Int){
        binding.gameFragmentBt1.visibility = View.INVISIBLE
        binding.gameFragmentBt2.visibility = View.INVISIBLE
        binding.gameFragmentBt3.visibility = View.INVISIBLE
        binding.gameFragmentBt4.visibility = View.INVISIBLE

        when(button){
            1 -> binding.gameFragmentBt1.visibility = View.VISIBLE
            2 -> binding.gameFragmentBt2.visibility = View.VISIBLE
            3 -> binding.gameFragmentBt3.visibility = View.VISIBLE
            else -> binding.gameFragmentBt4.visibility = View.VISIBLE
        }
    }

    //Change the score color
    private fun changeScoreColor(color: String){
        if (color == "purple"){
            binding.gameFragmentTvScore.setTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.purple_700))
        } else {
            binding.gameFragmentTvScore.setTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.my_red))
        }
    }

    //End the game
    private fun gameOver(){
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.score.value!!))
    }
}