package com.github.skytoph.simpleweather.domain.service.update

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

@AndroidEntryPoint
class UpdateForecastsService : Service() {

    private var handler: ServiceHandler? = null
    private val scope: CoroutineScope = CoroutineScope(EmptyCoroutineContext)

    @Inject
    lateinit var interactor: ServiceInteractor

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            scope.launch {
                try {
                    interactor.updateForecasts()
                } catch (e: Exception) {
                    Log.e(TAG, e.stackTraceToString())
                } finally {
                    stopSelf(msg.arg1)
                }
            }
        }
    }

    override fun onCreate() {
        HandlerThread(HANDLER_NAME, Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            handler = ServiceHandler(looper)
        }
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler?.obtainMessage()?.also { message ->
            message.arg1 = startId
            handler?.sendMessage(message)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }

    private companion object {
        const val TAG = "ErrorTag: Service"
        const val HANDLER_NAME = "UpdateForecastsService.ServiceHandler"
    }
}