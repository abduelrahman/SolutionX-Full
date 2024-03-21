package am.leon.solutionx.features.authentication.login.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login(
    val accessToken: String,
    val user: User
) : Parcelable