package com.kmalinowski.notification_spawner

import android.content.Context
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class NotificationMethodCallHandler(private val context: Context) : MethodChannel.MethodCallHandler {
    private val mapping = mapOf(
        "spawnSimple" to {call: MethodCall, result: MethodChannel.Result -> this.spawnSimple(call,result)}
    )

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (mapping.containsKey(call.method)) {
            mapping[call.method]!!.invoke(call,result)
        } else {
            result.notImplemented()
        }
    }

    private fun spawnSimple(call: MethodCall, result: MethodChannel.Result) {
        val service = NotificationService(context)
        service.spawnSimple()
        result.success(null)
    }
}