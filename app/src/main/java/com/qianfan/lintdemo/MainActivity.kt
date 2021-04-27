package com.qianfan.lintdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.telephony.TelephonyManager
import androidx.core.view.AccessibilityDelegateCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var value = YcUtils.open()
        Environment.getExternalStorageDirectory()
        val telephonyManager =
                getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager.deviceId
    }
}