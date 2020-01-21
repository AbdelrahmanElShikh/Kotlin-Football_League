package com.abdelrahman.football_league_kotlin.ui.destinations.teamDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdelrahman.football_league_kotlin.api.ApiResponse
import com.abdelrahman.football_league_kotlin.model.Team
import com.abdelrahman.football_league_kotlin.repository.PremierLeagueRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


/**
@author  Abdel-Rahman El-Shikh on 21-Jan-20.
 */
class TeamDetailsViewModel(private val premierLeagueRepository: PremierLeagueRepository
) : ViewModel(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Main + job

    val showLoading = MutableLiveData<Boolean>()
    val team = MutableLiveData<Team>()
    val showError = MutableLiveData<String>()

    fun loadTeamById(teamId: Int){
        showLoading.value = true
        launch {
            val result = withContext(IO){premierLeagueRepository.getTeamById(teamId = teamId)}
            showLoading.value = false
            when(result){
                is ApiResponse.Success -> team.value = result.data
                is ApiResponse.Error -> showError.value = result.exception.message
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}