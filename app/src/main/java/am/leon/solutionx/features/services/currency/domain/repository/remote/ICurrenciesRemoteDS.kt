package am.leon.solutionx.features.services.currency.domain.repository.remote

import am.leon.solutionx.features.services.currency.data.models.dto.CurrencyDto

internal interface ICurrenciesRemoteDS {
    fun getCurrencies(): List<CurrencyDto>
}