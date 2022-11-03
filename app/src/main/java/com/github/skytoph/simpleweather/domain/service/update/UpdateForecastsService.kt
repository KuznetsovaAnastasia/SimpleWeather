package com.github.skytoph.simpleweather.domain.service.update

import com.github.skytoph.simpleweather.domain.service.AbstractService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

@AndroidEntryPoint
class UpdateForecastsService : AbstractService() {

    private val scope: CoroutineScope = CoroutineScope(EmptyCoroutineContext)
    override val action: () -> Unit = { scope.launch { interactor.updateForecasts() } }
    override val handlerName: String = this::class.simpleName + ".Handler"

    @Inject
    lateinit var interactor: ServiceInteractor

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}