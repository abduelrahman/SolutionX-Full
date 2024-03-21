package am.leon.solutionx.features.services.currency.data.repository

import am.leon.solutionx.features.services.currency.data.mappers.CurrencyMapper
import am.leon.solutionx.features.services.currency.domain.models.Currency
import am.leon.solutionx.features.services.currency.domain.repository.ICurrenciesRepository
import am.leon.solutionx.features.services.currency.domain.repository.remote.ICurrenciesRemoteDS

internal class CurrenciesRepository(private val remoteDS: ICurrenciesRemoteDS) :
    ICurrenciesRepository {
    override fun getCurrenciesFromRemote(): List<Currency> {
        val result = remoteDS.getCurrencies()
        return CurrencyMapper.dtoToDomain(result)
    }
}