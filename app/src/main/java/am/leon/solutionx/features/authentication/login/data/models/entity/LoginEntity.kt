package am.leon.solutionx.features.authentication.login.data.models.entity

internal data class LoginEntity(
    val accessToken: String,
    val user: UserEntity
)