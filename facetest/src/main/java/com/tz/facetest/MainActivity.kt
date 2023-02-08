package com.tz.facetest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.tz.facetest.databinding.ActivityMainBinding
import com.tz.facetest.lanuchmodetest.FirstActivity
import com.tz.facetest.lifetest.Dialogctivity
import com.tz.facetest.lifetest.NormalActivity

class MainActivity : BaseActivity() {
    private lateinit var mBind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(this.javaClass.simpleName, "${this.toString()}--$taskId")
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(R.layout.activity_main)
        mBind.btnNormal.setOnClickListener {
//            val intent: Intent = Intent(this, NormalActivity::class.java)
            val intent: Intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }
        mBind.btnDialog.setOnClickListener {
//            val intent: Intent = Intent(this, Dialogctivity::class.java)
            val intent: Intent = Intent(this, Dialogctivity::class.java)
            startActivity(intent)
        }
    }
}