package com.tz.facetest.lanuchmodetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.tz.facetest.R
import com.tz.facetest.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var mBind: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_second)
        Log.d(this.javaClass.simpleName, "${this.toString()}--$taskId")
        mBind.btnToF.setOnClickListener { startActivity(Intent(this, ThirdActivity::class.java)) }

    }
}