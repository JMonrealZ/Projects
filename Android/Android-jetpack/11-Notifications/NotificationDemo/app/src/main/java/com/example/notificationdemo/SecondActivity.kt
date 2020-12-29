package com.example.notificationdemo

import android.app.Notification
import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        receiveveInput()
    }

    private fun receiveveInput(){

        val KEY_REPLAY = "key_reply"
        val channelID = "com.example.notificationdemo.channel1"
        val notificationId = 45

        //USAMOS DATOS QUE SE ENVIARON
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if(remoteInput!=null){
            val inputString = remoteInput.getCharSequence(KEY_REPLAY).toString()
            result_text.text = inputString
        }

        //ACTUALIZAMOS EL VIEW DE LA NOTIFICACION
        val repliedNotification = NotificationCompat.Builder(this, channelID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentText("Your reply received")
            .build()

        val notifivationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifivationManager.notify(notificationId,repliedNotification)
    }
}