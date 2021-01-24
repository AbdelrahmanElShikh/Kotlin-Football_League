package com.abdelrahman.football_league_kotlin.widget

import android.app.IntentService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.abdelrahman.football_league_kotlin.api.ApiService
import com.abdelrahman.football_league_kotlin.di.BASE_URL
import com.abdelrahman.football_league_kotlin.di.provideApiService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.context.GlobalContext.get
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 22-Jan-21
 * @Project : com.abdelrahman.football_league_kotlin.widget
 */
class TeamWidgetService() : JobIntentService() {

    companion object {
        val ACTION_UPDATE_TEAM_WIDGET =
            "com.abdelrahman.football_league_kotlin.widget.action.update_team_widget"
        val JOB_ID =2

        fun startActionUpdateTeamWidget(context: Context) {
            val intent = Intent(context, TeamWidgetService::class.java)
            intent.action = ACTION_UPDATE_TEAM_WIDGET
            enqueueWork(context,TeamWidgetService::class.java, JOB_ID,intent)
            //context.startService(intent)
        }
    }


    private fun handleActionUpdateTeamWidget(apiService: ApiService) {

        val teams = apiService.getPremierLeagueTeams()
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val appWidgetIds =
            appWidgetManager.getAppWidgetIds(ComponentName(this, TeamWidget::class.java))
        TeamWidget.updateAppWidget(
            context = this,
            appWidgetManager = appWidgetManager,
            appWidgetIds = appWidgetIds,
            teams = teams
        )
    }

    override fun onHandleWork(intent: Intent) {
        if (intent != null) {
            handleActionUpdateTeamWidget(apiService = provideApiService())
        }
    }


}
