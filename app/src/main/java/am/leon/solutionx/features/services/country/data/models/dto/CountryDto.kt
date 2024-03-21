package am.leon.solutionx.features.services.country.data.models.dto

import com.google.gson.annotations.SerializedName

internal data class CountryDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("currency") val currency: String? = null,
    @SerializedName("code") val code: String? = null,
    @SerializedName("phoneCode") val phoneCode: String? = null,
    @SerializedName("flag") val flag: String? = null
)