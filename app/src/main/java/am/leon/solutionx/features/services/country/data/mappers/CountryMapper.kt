package am.leon.solutionx.features.services.country.data.mappers

import am.leon.solutionx.common.data.mapper.Mapper
import am.leon.solutionx.features.services.country.data.models.dto.CountryDto
import am.leon.solutionx.features.services.country.data.models.entity.CountryEntity
import am.leon.solutionx.features.services.country.domain.models.Country

internal object CountryMapper : Mapper<CountryDto, Country, CountryEntity>() {
    override fun dtoToDomain(model: CountryDto): Country {
        return Country(
            id = model.id ?: -1,
            name = model.name.orEmpty(),
            currency = model.currency.orEmpty(),
            code = model.code.orEmpty(),
            phoneCode = model.phoneCode.orEmpty(),
            flag = model.flag.orEmpty(),
            selected = false
        )
    }

    override fun domainToEntity(model: Country): CountryEntity {
        return CountryEntity(
            id = model.id,
            name = model.name,
            currency = model.currency,
            code = model.code,
            phoneCode = model.phoneCode,
            flag = model.flag
        )
    }
}