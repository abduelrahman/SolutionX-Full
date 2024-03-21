package am.leon.solutionx.features.services.currency.data.repository.remote

import am.leon.solutionx.android.extentions.parseJsonFile
import am.leon.solutionx.features.services.currency.data.models.dto.CurrencyDto
import am.leon.solutionx.features.services.currency.domain.repository.remote.ICurrenciesRemoteDS
import android.content.Context

internal class CurrenciesRemoteDS(private val context: Context) : ICurrenciesRemoteDS {
    override fun getCurrencies(): List<CurrencyDto> {
        return parseJsonFile(context, "currencies.json")
    }
}