package am.leon.solutionx.features.authentication.login.domain.repository

import am.leon.solutionx.features.authentication.login.domain.models.Login

interface ILoginRepository {
    fun loginWithEmail(email: String, password: String): Login
    fun loginWithPhone(phone: String, password: String): Login
    fun saveLogin(login: Login)
}