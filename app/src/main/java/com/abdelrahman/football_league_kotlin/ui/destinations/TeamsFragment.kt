package com.abdelrahman.football_league_kotlin.ui.destinations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdelrahman.football_league_kotlin.R
import com.abdelrahman.football_league_kotlin.adapter.TeamAdapter
import kotlinx.android.synthetic.main.premier_league_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
class TeamsFragment: Fragment() {

    private val viewModel by viewModel<TeamsViewModel>()
    private lateinit var teamAdapter : TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.premier_league_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        teamAdapter = TeamAdapter()
        recyclerView_teams.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = teamAdapter
        }
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.premierLeagueTeams.observe(viewLifecycleOwner, Observer { teams ->
            teamAdapter.updateTeams(teams)
        })
        viewModel.showLoading.observe(viewLifecycleOwner, Observer { showLoading ->
            progressBar.visibility = if(showLoading) View.VISIBLE else View.GONE
        })
        viewModel.showError.observe(viewLifecycleOwner, Observer { showError ->
            Toast.makeText(activity,showError,Toast.LENGTH_SHORT).show()
        })
        viewModel.loadPremierLeagueTeams()
    }
}