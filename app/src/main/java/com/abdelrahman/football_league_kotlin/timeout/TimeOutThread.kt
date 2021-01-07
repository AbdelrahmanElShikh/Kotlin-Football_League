package com.abdelrahman.football_league_kotlin.timeout

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import java.lang.NullPointerException

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 07-Jan-21
 * @Project : com.abdelrahman.football_league_kotlin.timeout
 */
private const val TAG = "TimeOutThread"
class TimeOutThread(val context: Context,mainThreadHandler : Handler?) : Thread() {
    private var timeOutThreadHandler : TimeOutHandler? = null
    private var mainThreadHandler : Handler? = null
    private var isRunning : Boolean = false

    //Constructor
    init {
        this.mainThreadHandler = mainThreadHandler
        isRunning = true
    }

    override fun run() {
        if(isRunning){
            Looper.prepare()
            timeOutThreadHandler = TimeOutHandler(Looper.myLooper()!!,mainThreadHandler)
            Looper.loop()
        }
    }
    fun finishTimeOutThread(){
        isRunning = false
        mainThreadHandler = null
        timeOutThreadHandler!!.getTimer().cancel()
    }
    fun sendTimerMessageToTimeOutThread(message: Message){
        while (true){
            try {
                timeOutThreadHandler!!.sendMessage(message)
                break
            }catch (ex: NullPointerException){
                Log.e(TAG, "sendTimerMessageToTimeOutThread: Null Pointer" + ex.message )
                try {
                    sleep(100)
                }catch (ex: InterruptedException){
                    ex.printStackTrace()
                }
            }
        }
    }
}