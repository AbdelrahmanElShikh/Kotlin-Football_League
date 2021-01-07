package com.abdelrahman.football_league_kotlin.timeout

import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log


/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 07-Jan-21
 * @Project : com.abdelrahman.football_league_kotlin.timeout
 */
private const val TAG = "TimeOutHandler"

class TimeOutHandler(looper: Looper, val mainThreadHandler: Handler?) : Handler(looper) {
    var count = 1
    private val timer = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            Log.e(TAG, "onTick: $count")
            count++
        }

        override fun onFinish() {
            count = 1
            val message = Message.obtain(null, null)
            mainThreadHandler!!.sendMessage(message)
        }
    }

    override fun handleMessage(msg: Message) {
        Log.d(TAG, "handleMessage: is being called")

        timer.start()
    }

    fun getTimer(): CountDownTimer = timer

}