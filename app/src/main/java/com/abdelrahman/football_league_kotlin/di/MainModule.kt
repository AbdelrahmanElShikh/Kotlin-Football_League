package com.abdelrahman.football_league_kotlin.di

import com.abdelrahman.football_league_kotlin.repository.PremierLeagueRepository
import com.abdelrahman.football_league_kotlin.repository.PremierLeagueRepositoryImp
import com.abdelrahman.football_league_kotlin.ui.destinations.TeamsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
val mainModule = module {
//    single {
//        Room.databaseBuilder(androidApplication(),TeamRoomDatabase::class.java,"team_database")
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//    single { get<TeamRoomDatabase>().teamDao() }
    factory<PremierLeagueRepository> { PremierLeagueRepositoryImp(apiService = get()) }
    viewModel { TeamsViewModel(premierLeagueRepository = get(),application = get()) }

}