package com.abdelrahman.football_league_kotlin.api

import com.abdelrahman.football_league_kotlin.model.PremierLeague
import com.abdelrahman.football_league_kotlin.model.Team
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


/**
@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
interface ApiService {

    //Get Premier League Teams
    @Headers("X-Auth-Token: " + "ae449e21e9524d928dadbdc85df4022c")
    @GET("competitions/2021/teams")
    fun getPremierLeagueTeams(): Deferred<PremierLeague>


    //get specific team details by id.
    @Headers("X-Auth-Token: " + "ae449e21e9524d928dadbdc85df4022c")
    @GET("teams/{id}")
    fun getTeamById(@Path("id") teamId:Int): Deferred<Team>
}