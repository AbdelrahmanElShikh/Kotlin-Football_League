package com.abdelrahman.football_league_kotlin.ui.destinations.teamDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdelrahman.football_league_kotlin.R
import com.abdelrahman.football_league_kotlin.adapter.SquadAdapter
import com.abdelrahman.football_league_kotlin.databinding.TeamDetailsBinding
import kotlinx.android.synthetic.main.premier_league_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
@author  Abdel-Rahman El-Shikh on 21-Jan-20.
 */
class TeamDetailsFragment :Fragment(){
    private lateinit var binding : TeamDetailsBinding
    private val viewModel by viewModel<TeamDetailsViewModel>()
    private lateinit var squadAdapter: SquadAdapter
    private var teamId:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.team_details,container,false)
        teamId = TeamDetailsFragmentArgs.fromBundle(arguments!!).teamId
        squadAdapter = SquadAdapter()
        binding.recyclerViewSquad.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = squadAdapter
            isNestedScrollingEnabled = false
        }
        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        viewModel.team.observe(viewLifecycleOwner, Observer {team ->
            binding.team = team
            squadAdapter.updateSquade(team.squad)
        })
        viewModel.showLoading.observe(viewLifecycleOwner, Observer { showLoading ->
            progressBar.visibility = if(showLoading) View.VISIBLE else View.GONE
        })
        viewModel.showError.observe(viewLifecycleOwner, Observer { showError ->
            Toast.makeText(activity,showError, Toast.LENGTH_SHORT).show()
        })
        viewModel.loadTeamById(teamId)
    }
}