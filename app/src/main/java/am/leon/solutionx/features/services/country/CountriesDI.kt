package am.leon.solutionx.features.services.country

import am.leon.solutionx.features.services.country.data.repository.CountriesRepository
import am.leon.solutionx.features.services.country.data.repository.remote.CountriesRemoteDS
import am.leon.solutionx.features.services.country.domain.repository.ICountriesRepository
import am.leon.solutionx.features.services.country.domain.repository.remote.ICountriesRemoteDS
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
internal object CountriesDI {
    @Provides
    fun provideRemoteDS(@ApplicationContext context: Context): ICountriesRemoteDS =
        CountriesRemoteDS(context)

    @Provides
    fun provideRepository(remoteDS: ICountriesRemoteDS): ICountriesRepository =
        CountriesRepository(remoteDS)
}