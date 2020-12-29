package com.example.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //canal
    private val channelID = "com.example.notificationdemo.channel1"
    private var notificationManager:NotificationManager? = null
    private val KEY_REPLAY = "key_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID,"DemoChannel","this is a dmeo")

        button.setOnClickListener{
            displayNotification()
        }
    }

    private fun displayNotification(){
        val notificationId = 45 //whatever

        val tapResultIntent = Intent(this,SecondActivity::class.java)//.apply {
//            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
        val pendingIntent:PendingIntent = PendingIntent.getActivity(
                this,
                0,
                tapResultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT   //actualiza actual intent(si existe)
        )


        //region action button 1
        val Intent2 = Intent(this,DetailsActivity::class.java)
        val pendingIntent2:PendingIntent = PendingIntent.getActivity(
                this,
                0,
                Intent2,
                PendingIntent.FLAG_UPDATE_CURRENT   //actualiza actual intent(si existe)
        )
        val action2 : NotificationCompat.Action =
                NotificationCompat.Action.Builder(0,"Details",pendingIntent2).build()    //Se puede agregar imagen
        //endregion

        //region action button 2
        val Intent3 = Intent(this,SettingsActivity::class.java)
        val pendingIntent3:PendingIntent = PendingIntent.getActivity(
                this,
                0,
                Intent3,
                PendingIntent.FLAG_UPDATE_CURRENT   //actualiza actual intent(si existe)
        )
        val action3 : NotificationCompat.Action =
                NotificationCompat.Action.Builder(0,"Settings",pendingIntent3).build()    //Se puede agregar imagen
        //endregion

        //region reply action
        //objeto
        val remoteInput: RemoteInput = RemoteInput.Builder(KEY_REPLAY).run{
            setLabel("Insert text")     //hint
            build()
        }

        //action
        val replyAction : NotificationCompat.Action = NotificationCompat.Action.Builder(
            0,  //ningun icon
                "REPLY", //texto del botón
        pendingIntent
        ).addRemoteInput(remoteInput)
                .build()
        //endregion

        val notification = NotificationCompat.Builder(this,channelID)
                .setContentTitle("Demo title")
                .setContentText("This is a demo notification")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)    //se cancenla cuando el usuario da clic sobre él
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                //.setContentIntent(pendingIntent)
                .addAction(action2)
                .addAction(action3)
                .addAction(replyAction)
                .build()
        notificationManager?.notify(notificationId,notification)
    }

    private fun createNotificationChannel(id : String, name:String,channelDescription:String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id,name,importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }
}