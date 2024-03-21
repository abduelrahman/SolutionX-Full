package am.leon.solutionx.features.services.currency.domain.interactor

import am.leon.solutionx.common.data.models.Resource
import am.leon.solutionx.common.data.models.exception.LeonException
import am.leon.solutionx.features.services.currency.domain.models.Currency
import am.leon.solutionx.features.services.currency.domain.repository.ICurrenciesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrenciesUC @Inject constructor(private val repository: ICurrenciesRepository) {
    operator fun invoke(scope: CoroutineScope, onResult: (Resource<List<Currency>>) -> Unit) {
        scope.launch(Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            try {
                withContext(Dispatchers.IO) {
                    val countries = repository.getCurrenciesFromRemote()
                    onResult.invoke(Resource.success(countries))
                }
                withContext(Dispatchers.Main) {
                    onResult.invoke(Resource.loading(false))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.IO) {
                    val failureResource = if (e is LeonException)
                        e
                    else
                        LeonException.Unknown(message = "Unknown error in GetCurrenciesUC: $e")

                    onResult.invoke(Resource.failure(failureResource))
                }

                withContext(Dispatchers.Main) {
                    onResult.invoke(Resource.loading(false))
                }
            }
        }
    }

    operator fun invoke(): Flow<Resource<List<Currency>>> = channelFlow {
        send(Resource.loading())
        try {
            val countries = repository.getCurrenciesFromRemote()
            send(Resource.success(countries))
            send(Resource.loading(false))
        } catch (e: Exception) {
            val failureResource = if (e is LeonException) e
            else LeonException.Unknown(message = "Unknown error in GetCurrenciesUC: $e")

            send(Resource.failure(failureResource))
            send(Resource.loading(false))
        }
    }.flowOn(Dispatchers.IO)
}