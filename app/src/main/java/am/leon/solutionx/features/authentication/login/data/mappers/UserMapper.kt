package am.leon.solutionx.features.authentication.login.data.mappers

import am.leon.solutionx.common.data.mapper.Mapper
import am.leon.solutionx.features.authentication.login.data.models.dto.UserDto
import am.leon.solutionx.features.authentication.login.data.models.entity.UserEntity
import am.leon.solutionx.features.authentication.login.domain.models.User

internal object UserMapper : Mapper<UserDto, User, UserEntity>() {
    override fun dtoToDomain(model: UserDto): User {
        return User(
            id = model.id ?: -1,
            name = model.name.orEmpty(),
            email = model.email.orEmpty()
        )
    }

    override fun domainToEntity(model: User): UserEntity {
        return UserEntity(
            id = model.id,
            name = model.name,
            email = model.email
        )
    }
}