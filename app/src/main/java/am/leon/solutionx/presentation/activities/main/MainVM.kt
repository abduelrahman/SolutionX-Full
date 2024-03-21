package am.leon.solutionx.presentation.activities.main

import am.leon.solutionx.common.data.models.Resource
import am.leon.solutionx.features.services.country.domain.interactor.GetCountriesUC
import am.leon.solutionx.features.services.currency.domain.interactor.GetCurrenciesUC
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val countriesUC: GetCountriesUC, private val currenciesUC: GetCurrenciesUC,
) : ViewModel() {

    fun getCountries() {
        viewModelScope.launch {
            countriesUC.invoke().collect {
                when (it) {
                    is Resource.Failure -> TODO()
                    is Resource.Progress -> TODO()
                    is Resource.Success -> TODO()
                }
            }
        }

        countriesUC.invoke(viewModelScope) {
            when (it) {
                is Resource.Failure -> TODO()
                is Resource.Progress -> TODO()
                is Resource.Success -> TODO()
            }
        }
    }
}