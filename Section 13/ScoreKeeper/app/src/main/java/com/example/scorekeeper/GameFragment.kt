package com.example.scorekeeper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scorekeeper.databinding.FragmentGameBinding
import kotlin.math.round

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding

    //Counter variables
    var team1TotalShots = 0
    var team1MadeShots = 0

    var team2TotalShots = 0
    var team2MadeShots = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_game, container, false)
        binding = FragmentGameBinding.inflate(inflater)

        //Get data passed from previous frame
        val args = GameFragmentArgs.fromBundle(requireArguments())
        binding.gameFragmentTvTeam1Name.text = args.team1Name
        binding.gameFragmentTvTeam2Name.text = args.team2Name

        //Set click listeners
        binding.gameFragmentBtTeam1Ft.setOnClickListener { team1Shoot(1) }
        binding.gameFragmentBtTeam12pt.setOnClickListener { team1Shoot(2) }
        binding.gameFragmentBtTeam13pt.setOnClickListener { team1Shoot(3) }
        binding.gameFragmentBtTeam1Miss.setOnClickListener { team1Shoot(0) }

        binding.gameFragmentBtTeam2Ft.setOnClickListener { team2Shoot(1) }
        binding.gameFragmentBtTeam22pt.setOnClickListener { team2Shoot(2) }
        binding.gameFragmentBtTeam23pt.setOnClickListener { team2Shoot(3) }
        binding.gameFragmentBtTeam2Miss.setOnClickListener { team2Shoot(0) }

        binding.gameFragmentBtEnd.setOnClickListener { nextFragment() }

        return binding.root
    }

    //The first team has shot, update score and stats.
    private fun team1Shoot(pointValue: Int){
        if (pointValue != 0){
            team1MadeShots++

            //A shot was made, update the score
            val newScore = binding.gameFragmentTvTeam1Score.text.toString().toInt() + pointValue
            binding.gameFragmentTvTeam1Score.text = newScore.toString()

            //Update the quarter score
            when {
                binding.gameFragmentRbQ1.isChecked -> {
                    val quarterScore = binding.gameFragmentTvTeam1Q1.text.toString().toInt() + pointValue
                    binding.gameFragmentTvTeam1Q1.text = quarterScore.toString()
                }
                binding.gameFragmentRbQ2.isChecked -> {
                    val quarterScore = binding.gameFragmentTvTeam1Q2.text.toString().toInt() + pointValue
                    binding.gameFragmentTvTeam1Q2.text = quarterScore.toString()
                }
                binding.gameFragmentRbQ3.isChecked -> {
                    val quarterScore = binding.gameFragmentTvTeam1Q3.text.toString().toInt() + pointValue
                    binding.gameFragmentTvTeam1Q3.text = quarterScore.toString()
                } else -> {
                val quarterScore = binding.gameFragmentTvTeam1Q4.text.toString().toInt() + pointValue
                binding.gameFragmentTvTeam1Q4.text = quarterScore.toString()
                }
            }
        }

        //Update the shooting percentages
        team1TotalShots++

        val shootingPercentage = round(team1MadeShots.toFloat()/team1TotalShots * 10000)/100
        binding.gameFragmentTvTeam1Percentage.text = "$shootingPercentage %"
    }

    //The second team has shot, update score and stats.
    private fun team2Shoot(pointValue: Int){
        if (pointValue != 0){
            team2MadeShots++

            //A shot was made, update the score
            val newScore = binding.gameFragmentTvTeam2Score.text.toString().toInt() + pointValue
            binding.gameFragmentTvTeam2Score.text = newScore.toString()

            //Update the quarter score
            when {
                binding.gameFragmentRbQ1.isChecked -> {
                    val quarterScore = binding.gameFragmentTvTeam2Q1.text.toString().toInt() + pointValue
                    binding.gameFragmentTvTeam2Q1.text = quarterScore.toString()
                }
                binding.gameFragmentRbQ2.isChecked -> {
                    val quarterScore = binding.gameFragmentTvTeam2Q2.text.toString().toInt() + pointValue
                    binding.gameFragmentTvTeam2Q2.text = quarterScore.toString()
                }
                binding.gameFragmentRbQ3.isChecked -> {
                    val quarterScore = binding.gameFragmentTvTeam2Q3.text.toString().toInt() + pointValue
                    binding.gameFragmentTvTeam2Q3.text = quarterScore.toString()
                } else -> {
                val quarterScore = binding.gameFragmentTvTeam2Q4.text.toString().toInt() + pointValue
                binding.gameFragmentTvTeam2Q4.text = quarterScore.toString()
            }
            }
        }

        //Update the shooting percentages
        team2TotalShots++

        val shootingPercentage = round(team2MadeShots.toFloat()/team2TotalShots * 10000)/100
        binding.gameFragmentTvTeam2Percentage.text = "$shootingPercentage %"
    }

    //Move to the next fragment
    private fun nextFragment(){
        val team1 = binding.gameFragmentTvTeam1Name.text.toString()
        val score1 = binding.gameFragmentTvTeam1Score.text.toString()
        val team2 = binding.gameFragmentTvTeam2Name.text.toString()
        val score2 = binding.gameFragmentTvTeam2Score.text.toString()

        findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(team1, score1, team2, score2))

    }
}