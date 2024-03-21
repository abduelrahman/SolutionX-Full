package am.leon.solutionx.features.services.currency.data.models.dto

import com.google.gson.annotations.SerializedName

internal data class CurrencyDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("sign") val sign: String? = null,
    @SerializedName("code") val code: String? = null
)