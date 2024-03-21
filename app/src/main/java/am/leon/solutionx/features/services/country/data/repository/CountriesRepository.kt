package am.leon.solutionx.features.services.country.data.repository

import am.leon.solutionx.features.services.country.data.mappers.CountryMapper
import am.leon.solutionx.features.services.country.domain.models.Country
import am.leon.solutionx.features.services.country.domain.repository.ICountriesRepository
import am.leon.solutionx.features.services.country.domain.repository.remote.ICountriesRemoteDS

internal class CountriesRepository(private val remoteDS: ICountriesRemoteDS): ICountriesRepository {
    override fun getCountriesFromRemote(): List<Country> {
        val result = remoteDS.getCountries()
        return CountryMapper.dtoToDomain(result)
    }
}