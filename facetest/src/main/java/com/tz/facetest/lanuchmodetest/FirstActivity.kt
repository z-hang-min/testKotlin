package com.tz.facetest.lanuchmodetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.tz.facetest.R
import com.tz.facetest.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    lateinit var mBind: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_first)
        Log.d(this.javaClass.simpleName, "${this.toString()}--$taskId")
        mBind.btnToS.setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }
    }
}