package com.abdelrahman.football_league_kotlin.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
@author  Abdel-Rahman El-Shikh on 21-Jan-20.
 */
class DataTypeConverter {
    companion object{
        @TypeConverter
        @JvmStatic
        fun toListSquads(value: String):List<Squad>?{
            val typeList = object : TypeToken<List<Squad>>() {}.type
            return Gson().fromJson(value,typeList)
        }

        @TypeConverter
        @JvmStatic
        fun fromListSquads(squads: List<Squad>):String?{
            val gson = Gson()
            val json = gson.toJson(squads)
            return json
        }
    }
}