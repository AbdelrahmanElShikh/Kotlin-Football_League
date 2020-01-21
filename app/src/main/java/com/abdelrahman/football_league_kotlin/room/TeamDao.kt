package com.abdelrahman.football_league_kotlin.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdelrahman.football_league_kotlin.model.Squad
import com.abdelrahman.football_league_kotlin.model.Team


/**
 * @author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
@Dao
interface TeamDao {
    @Query("SELECT * FROM teams")
    fun getTeams(): List<Team>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(teams: List<Team>)

    @Query("UPDATE teams SET squad =:squads WHERE id =:givenId")
    fun updateSquad(givenId:Int,squads: List<Squad>)

    @Query("SELECT * FROM teams WHERE id=:givenId")
    fun getTeamById(givenId: Int):Team
}