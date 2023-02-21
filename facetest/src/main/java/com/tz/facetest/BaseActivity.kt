package com.tz.facetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

open class BaseActivity : AppCompatActivity() {

    private val tag=javaClass.simpleName
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(tag,"onNewIntent()")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag,"onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag,"onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag,"onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag,"onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag,"onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag,"onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag,"onDestroy()")
    }
}
