package com.abdelrahman.football_league_kotlin.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
@author  Abdel-Rahman El-Shikh on 19-Jan-20.
 */
@Entity(tableName = "teams")
data class Team(
    @PrimaryKey
    var id: Int,
    var address: String?,
    var clubColors: String?,
    var crestUrl: String?,
    var email: String?,
    var founded: Int?,
    var lastUpdated: String?,
    var name: String?,
    var phone: String?,
    var shortName: String?,
    @Ignore
    var squad: List<Squad>?,
    var tla: String?,
    var venue: String?,
    var website: String?
){
    constructor() : this(0, "", "", "", "", 0, "", "","","",null,"","","")
}
