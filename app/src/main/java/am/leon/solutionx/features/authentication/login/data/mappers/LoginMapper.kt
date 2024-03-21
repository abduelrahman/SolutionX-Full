package am.leon.solutionx.features.authentication.login.data.mappers

import am.leon.solutionx.common.data.mapper.Mapper
import am.leon.solutionx.features.authentication.login.data.models.dto.LoginDto
import am.leon.solutionx.features.authentication.login.data.models.dto.UserDto
import am.leon.solutionx.features.authentication.login.data.models.entity.LoginEntity
import am.leon.solutionx.features.authentication.login.domain.models.Login

internal object LoginMapper : Mapper<LoginDto, Login, LoginEntity>() {
    override fun dtoToDomain(model: LoginDto): Login {
        return Login(
            accessToken = model.accessToken.orEmpty(),
            user = UserMapper.dtoToDomain(model.user ?: UserDto())
        )
    }

    override fun domainToEntity(model: Login): LoginEntity {
        return LoginEntity(
            accessToken = model.accessToken,
            user = UserMapper.domainToEntity(model.user)
        )
    }
}