package com.abdelrahman.football_league_kotlin.ui.destinations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdelrahman.football_league_kotlin.api.ApiResponse
import com.abdelrahman.football_league_kotlin.model.Team
import com.abdelrahman.football_league_kotlin.repository.PremierLeagueRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.coroutines.CoroutineContext


/**
@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
class TeamsViewModel(private val premierLeagueRepository: PremierLeagueRepository) : ViewModel(),
    CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val premierLeagueTeams = MutableLiveData<List<Team>>()
    val showError = MutableLiveData<String>()
//    val teamRoomDatabase = TeamRoomDatabase.getDatabase(application)
//    val teamDao = teamRoomDatabase.teamDao()

    fun loadPremierLeagueTeams(){
        showLoading.value = true
        launch {
            val result = withContext(IO){premierLeagueRepository.getPremierLeagueTeams()}
            showLoading.value = false
            when(result){
                is ApiResponse.Success ->premierLeagueTeams.value = result.data
                is ApiResponse.Error -> showError.value = result.exception.message
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}