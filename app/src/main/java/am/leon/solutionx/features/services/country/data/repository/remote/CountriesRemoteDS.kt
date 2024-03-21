package am.leon.solutionx.features.services.country.data.repository.remote

import am.leon.solutionx.android.extentions.parseJsonFile
import am.leon.solutionx.features.services.country.data.models.dto.CountryDto
import am.leon.solutionx.features.services.country.domain.repository.remote.ICountriesRemoteDS
import android.content.Context

internal class CountriesRemoteDS(private val context: Context) : ICountriesRemoteDS {
    override fun getCountries(): List<CountryDto> {
        return parseJsonFile(context, "countries.json")
    }
}