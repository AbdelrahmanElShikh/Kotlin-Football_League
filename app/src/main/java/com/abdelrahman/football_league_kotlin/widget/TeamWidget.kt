package com.abdelrahman.football_league_kotlin.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.abdelrahman.football_league_kotlin.R
import com.abdelrahman.football_league_kotlin.model.PremierLeague
import com.abdelrahman.football_league_kotlin.ui.MainActivity
import com.abdelrahman.football_league_kotlin.ui.destinations.teams.TeamsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Implementation of App Widget functionality.
 */
class TeamWidget : AppWidgetProvider() {
    companion object {
        fun updateTeamWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int,
            teams: Deferred<PremierLeague>
        ) {
            CoroutineScope(Dispatchers.Default).launch{
                val views = RemoteViews(context.packageName, R.layout.team_widget)
                views.setTextViewText(R.id.appwidget_text, teams.await().teams[0].name);
                val intent = Intent(context, MainActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
                views.setOnClickPendingIntent(R.id.widget_container, pendingIntent)
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }

         fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetIds: IntArray,
            teams: Deferred<PremierLeague>
        ) {
            for (appWidgetId in appWidgetIds) {
                updateTeamWidget(context, appWidgetManager, appWidgetId,teams)
            }
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val views = RemoteViews(context.packageName, R.layout.team_widget)
        views.setTextViewText(R.id.appwidget_text,"loading");
        TeamWidgetService.startActionUpdateTeamWidget(context)

    }


    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}


