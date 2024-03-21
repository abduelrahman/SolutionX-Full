package am.leon.solutionx.features.services.country.domain.interactor

import am.leon.solutionx.common.data.models.Resource
import am.leon.solutionx.common.data.models.exception.LeonException
import am.leon.solutionx.features.services.country.domain.models.Country
import am.leon.solutionx.features.services.country.domain.repository.ICountriesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCountriesUC @Inject constructor(private val repository: ICountriesRepository) {
    operator fun invoke(scope: CoroutineScope, onResult: (Resource<List<Country>>) -> Unit) {
        scope.launch(Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            try {
                withContext(Dispatchers.IO) {
                    val countries = repository.getCountriesFromRemote()
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
                        LeonException.Unknown(message = "Unknown error in GetCountriesUC: $e")

                    onResult.invoke(Resource.failure(failureResource))
                }

                withContext(Dispatchers.Main) {
                    onResult.invoke(Resource.loading(false))
                }
            }
        }
    }

    operator fun invoke(): Flow<Resource<List<Country>>> = channelFlow {
        send(Resource.loading())
        try {
            val countries = repository.getCountriesFromRemote()
            send(Resource.success(countries))
            send(Resource.loading(false))
        } catch (e: Exception) {
            val failureResource = if (e is LeonException) e
            else LeonException.Unknown(message = "Unknown error in GetCountriesUC: $e")

            send(Resource.failure(failureResource))
            send(Resource.loading(false))
        }
    }.flowOn(Dispatchers.IO)
}