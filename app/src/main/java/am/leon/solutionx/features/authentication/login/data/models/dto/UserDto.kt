package am.leon.solutionx.features.authentication.login.data.models.dto

import com.google.gson.annotations.SerializedName

internal data class UserDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("email") val email: String? = null
)