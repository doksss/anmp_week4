package com.example.studentapp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log

fun createNotificationChannel(context: Context, importance:Int, showBadge:Boolean, name: String, description: String){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId,name,importance)
        channel.description = description
        channel.setShowBadge(showBadge)

        Log.d("checkchannel","masuk util")
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }else{
        Log.d("checkchannel","masuk util else")
    }
}