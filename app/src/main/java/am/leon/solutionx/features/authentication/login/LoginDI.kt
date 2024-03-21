package am.leon.solutionx.features.authentication.login

import am.leon.solutionx.features.authentication.login.data.repository.LoginRepository
import am.leon.solutionx.features.authentication.login.data.repository.local.LoginLocalDS
import am.leon.solutionx.features.authentication.login.data.repository.remote.LoginRemoteDS
import am.leon.solutionx.features.authentication.login.domain.interactor.LoginWithEmailUC
import am.leon.solutionx.features.authentication.login.domain.repository.ILoginRepository
import am.leon.solutionx.features.authentication.login.domain.repository.local.ILoginLocalDS
import am.leon.solutionx.features.authentication.login.domain.repository.remote.ILoginRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object LoginDI {

    @Provides
    fun provideRemoteDS(): ILoginRemoteDS = LoginRemoteDS()

    @Provides
    fun provideLocalDS(): ILoginLocalDS = LoginLocalDS()

    @Provides
    fun provideRepository(remoteDS: ILoginRemoteDS, localDS: ILoginLocalDS): ILoginRepository =
        LoginRepository(remoteDS, localDS)

    @Provides
    fun provideLoginWithEmailUC(repository: ILoginRepository): LoginWithEmailUC =
        LoginWithEmailUC(repository)
}