package com.abdelrahman.football_league_kotlin.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abdelrahman.football_league_kotlin.model.DataTypeConverter
import com.abdelrahman.football_league_kotlin.model.Team


/**
@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
@Database(entities = [Team::class], version = 1)
@TypeConverters(DataTypeConverter::class)
abstract class TeamRoomDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao

    companion object {
        /** Singleton prevents multiple instances of database opening at the
         *   same time.
         */
        @Volatile // meaning that writes to this field are immediately made visible to other threads.
        private var INSTANCE: TeamRoomDatabase? = null

        fun getDatabase(context: Context): TeamRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamRoomDatabase::class.java,
                    "team_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}