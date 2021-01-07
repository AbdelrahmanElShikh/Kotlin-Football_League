package com.abdelrahman.football_league_kotlin.ui

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.abdelrahman.football_league_kotlin.R
import com.abdelrahman.football_league_kotlin.timeout.TimeOutThread
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Handler.Callback {
    private var timeOutThread: TimeOutThread? = null
    private var mainThreadHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        mainThreadHandler = Handler(this)
        startTimeOut()
    }

    private fun startTimeOut() {
        timeOutThread = TimeOutThread(this, mainThreadHandler)
        timeOutThread!!.start()
        val message = Message.obtain(null, null)
        timeOutThread!!.sendTimerMessageToTimeOutThread(message)
    }


    override fun onDestroy() {
        super.onDestroy()
        timeOutThread!!.finishTimeOutThread()
    }

    override fun handleMessage(msg: Message): Boolean {
        AlertDialog.Builder(this).setTitle("Session TimeOut")
            .setMessage("Your Time has come to close the app you IDIOT")
            .setCancelable(false)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, which -> finish() }
            .show()
        //Toast.makeText(this,"Timer Finished ${resources.configuration.orientation}",Toast.LENGTH_SHORT).show()
        return true
    }
}
