package com.abdelrahman.football_league_kotlin.repository

import com.abdelrahman.football_league_kotlin.api.ApiResponse
import com.abdelrahman.football_league_kotlin.model.Team


/**
@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
interface PremierLeagueRepository {
    suspend fun getPremierLeagueTeams(): ApiResponse<List<Team>>
    suspend fun getTeamById(teamId:Int): ApiResponse<Team>
}