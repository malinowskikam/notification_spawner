package com.kmalinowski.notification_spawner

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    private val channel = "com.kmalinowski.notification_spawner/notifications"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Dupa")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationService(this).createNotificationChannels()
        } else {
            println("Dupa")
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channel).setMethodCallHandler(
            NotificationMethodCallHandler(this)
        )
    }
}
