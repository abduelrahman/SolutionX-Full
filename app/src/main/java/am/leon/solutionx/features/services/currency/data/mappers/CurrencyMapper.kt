package am.leon.solutionx.features.services.currency.data.mappers

import am.leon.solutionx.common.data.mapper.Mapper
import am.leon.solutionx.features.services.currency.data.models.dto.CurrencyDto
import am.leon.solutionx.features.services.currency.data.models.entity.CurrencyEntity
import am.leon.solutionx.features.services.currency.domain.models.Currency

internal object CurrencyMapper : Mapper<CurrencyDto, Currency, CurrencyEntity>() {
    override fun dtoToDomain(model: CurrencyDto): Currency {
        return Currency(
            id = model.id ?: -1,
            name = model.name.orEmpty(),
            sign = model.sign.orEmpty(),
            code = model.code.orEmpty(),
            selected = false
        )
    }

    override fun domainToEntity(model: Currency): CurrencyEntity {
        return CurrencyEntity(
            id = model.id,
            name = model.name,
            sign = model.sign,
            code = model.code
        )
    }
}