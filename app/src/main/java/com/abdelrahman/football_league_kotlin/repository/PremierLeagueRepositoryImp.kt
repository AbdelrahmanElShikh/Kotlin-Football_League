package com.abdelrahman.football_league_kotlin.repository

import android.util.Log
import com.abdelrahman.football_league_kotlin.api.ApiResponse
import com.abdelrahman.football_league_kotlin.api.ApiService
import com.abdelrahman.football_league_kotlin.model.Team
import com.abdelrahman.football_league_kotlin.room.TeamDao


/**
@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
class PremierLeagueRepositoryImp(private val apiService: ApiService,private val teamDao:TeamDao) :
    PremierLeagueRepository {
    override suspend fun getPremierLeagueTeams(): ApiResponse<List<Team>> {
        return try {
            val result = teamDao.getTeams()
            if (result.isEmpty()) return loadTeamsFromApi()
            ApiResponse.Success(result)
        } catch (ex: Exception) {
            Log.e("PremierLeagueRepoImp1", ex.localizedMessage!!)
            ApiResponse.Error(ex)
        }
    }

    private suspend fun loadTeamsFromApi(): ApiResponse<List<Team>> {
        return try {
            val result = apiService.getPremierLeagueTeams().await()
            teamDao.insertTeams(result.teams)
            ApiResponse.Success(result.teams)
        } catch (ex: Exception) {
            Log.e("PremierLeagueRepoImp2", ex.localizedMessage!!)
            ApiResponse.Error(ex)
        }
    }

    override suspend fun getTeamById(teamId: Int): ApiResponse<Team> {
        return try {
            val result = apiService.getTeamById(teamId = teamId).await()
            ApiResponse.Success(result)
        } catch (ex: Exception) {
            ApiResponse.Error(ex)
        }
    }
}