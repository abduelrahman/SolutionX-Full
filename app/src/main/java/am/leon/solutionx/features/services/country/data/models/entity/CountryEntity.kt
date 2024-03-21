package am.leon.solutionx.features.services.country.data.models.entity

internal data class CountryEntity(
    val id: Int,
    val name: String,
    val currency: String,
    val code: String,
    val phoneCode: String,
    val flag: String
)