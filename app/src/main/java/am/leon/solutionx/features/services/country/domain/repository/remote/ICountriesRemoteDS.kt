package am.leon.solutionx.features.services.country.domain.repository.remote

import am.leon.solutionx.features.services.country.data.models.dto.CountryDto

internal interface ICountriesRemoteDS {
    fun getCountries(): List<CountryDto>
}