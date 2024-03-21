package am.leon.solutionx.features.services.currency.domain.repository

import am.leon.solutionx.features.services.currency.domain.models.Currency

interface ICurrenciesRepository {
    fun getCurrenciesFromRemote(): List<Currency>
}