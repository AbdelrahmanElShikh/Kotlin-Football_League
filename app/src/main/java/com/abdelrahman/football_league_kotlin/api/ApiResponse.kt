package com.abdelrahman.football_league_kotlin.api


/**
@author  Abdel-Rahman El-Shikh on 20-Jan-20.
 */
sealed class ApiResponse<out T:Any> {
    /**
     * When we want to assign the generic type to any of its super type, then we need to use “out” keyWord.
     */
    class Success<out T : Any>(val data:T): ApiResponse<T>()
    class Error(val exception:Throwable): ApiResponse<Nothing>()
}