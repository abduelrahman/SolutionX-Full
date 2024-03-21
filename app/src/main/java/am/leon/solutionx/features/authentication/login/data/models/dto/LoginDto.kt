package am.leon.solutionx.features.authentication.login.data.models.dto

import com.google.gson.annotations.SerializedName

internal data class LoginDto(
    @SerializedName("access_token") val accessToken: String? = null,
    @SerializedName("user") val user: UserDto? = null
)