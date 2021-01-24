package com.abdelrahman.football_league_kotlin.di

import com.abdelrahman.football_league_kotlin.repository.PremierLeagueRepository
import com.abdelrahman.football_league_kotlin.repository.PremierLeagueRepositoryImp
import com.abdelrahman.football_league_kotlin.room.TeamRoomDatabase
import com.abdelrahman.football_league_kotlin.ui.destinations.teamDetails.TeamDetailsViewModel
import com.abdelrahman.football_league_kotlin.ui.destinations.teams.TeamsViewModel
import com.abdelrahman.football_league_kotlin.widget.TeamWidgetService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin


/**
@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
val mainModule = module {
    single {
        TeamRoomDatabase.getDatabase(get())
    }
    single { get<TeamRoomDatabase>().teamDao() }
    factory<PremierLeagueRepository> {
        PremierLeagueRepositoryImp(
            apiService = get(),
            teamDao = get()
        )
    }
    viewModel {
        TeamsViewModel(
            premierLeagueRepository = get()
        )
    }
    viewModel {
        TeamDetailsViewModel(premierLeagueRepository = get())
    }

}