package com.example.studentapp.view

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.studentapp.R
import com.example.studentapp.databinding.ActivityMainBinding
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.example.studentapp.util.createNotificationChannel

class MainActivity : AppCompatActivity() {
    //Week 7
    init {
        instance = this
    }
    companion object{
        private var instance:MainActivity ?= null
        fun showNotification(title:String, content:String, icon:Int){
            //isi value id bebas sih ga harus ini
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val builder = NotificationCompat.Builder(instance!!.applicationContext,channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }
            val manager = NotificationManagerCompat.from(instance!!.applicationContext)
            if(ActivityCompat.checkSelfPermission(instance!!.applicationContext,
                Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(instance!!,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),1)//Request code angkanya bebas tpi harus diinget
                return
            }
            //nilai id nya bebas
            Log.d("checkchannel","masuk")
            manager.notify(1001,builder.build()) //code display notifikasi ke layar, yg panjang yg atasnya
        }
    }

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel(this,NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name),"App notification channel.")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 1){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("Permission check", "granted")
                //create notif channel
                createNotificationChannel(this,NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                    getString(R.string.app_name),"App notification channel.")
            }else{
                Log.d("permission check","deny")
            }
        }
    }
}