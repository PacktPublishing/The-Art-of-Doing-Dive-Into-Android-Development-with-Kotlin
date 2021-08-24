package com.example.scorekeeper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scorekeeper.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {
    private lateinit var binding: FragmentGameOverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_game_over, container, false)
        binding = FragmentGameOverBinding.inflate(inflater)

        //Get the data passed to the fragment
        val args = GameOverFragmentArgs.fromBundle(requireArguments())
        binding.gameOverFragmentTvTeam1Score.text = "${args.team1Name} ${args.team1Score} Points"
        binding.gameOverFragmentTvTeam2Score.text = "${args.team2Name} ${args.team2Score} Points"

        if (args.team1Score.toInt() > args.team2Score.toInt()){
            binding.gameOverFragmentTvWinner.text = "${args.team1Name} Win!!!"
            binding.gameOverFragmentTvWinner.setTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.team1))
        } else {
            binding.gameOverFragmentTvWinner.text = "${args.team2Name} Win!!!"
            binding.gameOverFragmentTvWinner.setTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.team2))
        }

        //Set click listeners
        binding.gameOverFragmentBtNewGame.setOnClickListener {
            findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToTitleFragment())
        }

        return binding.root
    }
}