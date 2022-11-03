package com.github.skytoph.simpleweather.domain.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

abstract class AbstractService : Service() {

    protected abstract val handlerName: String
    protected abstract val action: () -> Unit
    private var handler: ServiceHandler? = null

    private inner class ServiceHandler(looper: Looper, private val action: () -> Unit) :
        Handler(looper) {
        override fun handleMessage(msg: Message) {
            Log.d(TAG, "handleMessage: service started")
            try {
                action
            } catch (e: Exception) {
                Log.e(TAG, e.stackTraceToString())
            } finally {
                stopSelf(msg.arg1)
            }
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate: service created")
        HandlerThread(handlerName, Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            handler = ServiceHandler(looper, action)
        }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler?.obtainMessage()?.also { message ->
            message.arg1 = startId
            handler?.sendMessage(message)
        }
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? = null

    private companion object {
        const val TAG = "ErrorTag: Service"
    }
}