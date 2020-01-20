package com.abdelrahman.football_league_kotlin.model


/**
@author  Abdel-Rahman El-Shikh on 19-Jan-20.
 */
data class Squad(
    val countryOfBirth: String,
    val dateOfBirth: String,
    val id: Int,
    val name: String,
    val nationality: String,
    val position: String,
    val role: String,
    val shirtNumber: Int
)