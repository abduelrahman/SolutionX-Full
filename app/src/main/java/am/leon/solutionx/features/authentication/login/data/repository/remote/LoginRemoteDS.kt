package am.leon.solutionx.features.authentication.login.data.repository.remote

import am.leon.solutionx.features.authentication.login.data.models.dto.LoginDto
import am.leon.solutionx.features.authentication.login.domain.repository.remote.ILoginRemoteDS

// [feature]  layer

internal class LoginRemoteDS() : ILoginRemoteDS {
    override fun loginWithEmail(email: String, password: String): LoginDto {
        val body = hashMapOf(
            "email" to email,
            "password" to password
        )
        TODO("Not yet implemented")
    }

    override fun loginWithPhone(phone: String, password: String): LoginDto {
        TODO("Not yet implemented")
    }
}