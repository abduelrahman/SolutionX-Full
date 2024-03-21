package am.leon.solutionx.features.services.currency

import am.leon.solutionx.features.services.currency.data.repository.CurrenciesRepository
import am.leon.solutionx.features.services.currency.data.repository.remote.CurrenciesRemoteDS
import am.leon.solutionx.features.services.currency.domain.repository.ICurrenciesRepository
import am.leon.solutionx.features.services.currency.domain.repository.remote.ICurrenciesRemoteDS
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
internal object CurrenciesDI {
    @Provides
    fun provideRemoteDS(@ApplicationContext context: Context): ICurrenciesRemoteDS =
        CurrenciesRemoteDS(context)

    @Provides
    fun provideRepository(remoteDS: ICurrenciesRemoteDS): ICurrenciesRepository =
        CurrenciesRepository(remoteDS)
}