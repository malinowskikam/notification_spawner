package com.kmalinowski.notification_spawner

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

class NotificationService(private val context: Context) {
    private val testChannelId = "notification_test_channel"

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannels() {
        val name = "Test Channel"
        val descriptionText = "Test Desctiption"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(testChannelId, name, importance).apply {
            description = descriptionText
            enableLights(true)
            enableVibration(true)
            lightColor = Color.GREEN;
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }


    fun spawnSimple() {
        val builder = NotificationCompat.Builder(context, testChannelId)
            .setSmallIcon(R.drawable.launch_background)
            .setContentTitle("Test Title")
            .setContentText("Test Content")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true);

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(Random.nextInt(), builder.build())
        }
    }
}