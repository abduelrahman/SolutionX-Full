package am.leon.solutionx.features.authentication.login.domain.repository.remote

import am.leon.solutionx.features.authentication.login.data.models.dto.LoginDto

// I --> interface  [feature]  layer

internal interface ILoginRemoteDS {
    fun loginWithEmail(email: String, password: String): LoginDto
    fun loginWithPhone(phone: String, password: String): LoginDto
}