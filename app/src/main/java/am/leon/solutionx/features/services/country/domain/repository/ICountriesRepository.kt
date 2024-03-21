package am.leon.solutionx.features.services.country.domain.repository

import am.leon.solutionx.features.services.country.domain.models.Country

interface ICountriesRepository {
    fun getCountriesFromRemote(): List<Country>
}