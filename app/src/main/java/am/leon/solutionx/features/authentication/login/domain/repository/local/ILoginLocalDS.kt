package am.leon.solutionx.features.authentication.login.domain.repository.local

import am.leon.solutionx.features.authentication.login.data.models.entity.LoginEntity

// I --> interface  [feature]  layer

internal interface ILoginLocalDS {
    fun saveLogin(login: LoginEntity)
}