package com.abdelrahman.football_league_kotlin

import android.app.Application
import com.abdelrahman.football_league_kotlin.di.apiModule
import com.abdelrahman.football_league_kotlin.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(apiModule, mainModule)
        }
    }
}