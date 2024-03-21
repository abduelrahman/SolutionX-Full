package am.leon.solutionx.features.authentication.login.data.repository

import am.leon.solutionx.features.authentication.login.data.mappers.LoginMapper
import am.leon.solutionx.features.authentication.login.domain.models.Login
import am.leon.solutionx.features.authentication.login.domain.repository.ILoginRepository
import am.leon.solutionx.features.authentication.login.domain.repository.local.ILoginLocalDS
import am.leon.solutionx.features.authentication.login.domain.repository.remote.ILoginRemoteDS

internal class LoginRepository(
    private val remoteDS: ILoginRemoteDS, private val localDS: ILoginLocalDS
) : ILoginRepository {
    override fun loginWithEmail(email: String, password: String): Login {
        val result = remoteDS.loginWithEmail(email, password)
        return LoginMapper.dtoToDomain(result)
    }

    override fun loginWithPhone(phone: String, password: String): Login {
        val result = remoteDS.loginWithPhone(phone, password)
        return LoginMapper.dtoToDomain(result)
    }

    override fun saveLogin(login: Login) {
        val result = LoginMapper.domainToEntity(login)
        localDS.saveLogin(result)
    }
}