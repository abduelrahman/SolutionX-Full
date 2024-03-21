package am.leon.solutionx.presentation.activities.main

import am.leon.solutionx.common.data.models.Resource
import am.leon.solutionx.common.presentation.SolutionXViewModel
import am.leon.solutionx.common.presentation.ViewAction
import am.leon.solutionx.features.services.country.domain.interactor.GetCountriesUC
import am.leon.solutionx.features.services.currency.domain.interactor.GetCurrenciesUC
import am.leon.solutionx.presentation.activities.main.MainViewContract.MainAction
import am.leon.solutionx.presentation.activities.main.MainViewContract.MainEvent
import am.leon.solutionx.presentation.activities.main.MainViewContract.MainState
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val countriesUC: GetCountriesUC, private val currenciesUC: GetCurrenciesUC,
) : SolutionXViewModel<MainAction, MainEvent, MainState>(MainState.initial()) {

    fun getCountries() {
        viewModelScope.launch {
            countriesUC.invoke().collect {
                when (it) {
                    is Resource.Failure -> setState(oldViewState.copy(exception = it.exception))
                    is Resource.Progress -> setState(oldViewState.copy(isLoading = it.loading))
                    is Resource.Success -> sendEvent(MainEvent.CountriesIndex(it.model))
                }
            }
        }

        countriesUC.invoke(viewModelScope) {
            when (it) {
                is Resource.Failure -> setState(oldViewState.copy(exception = it.exception))
                is Resource.Progress -> setState(oldViewState.copy(isLoading = it.loading))
                is Resource.Success -> sendEvent(MainEvent.CountriesIndex(it.model))
            }
        }
    }

    private fun loginWithEmail(email: String, password: String) {

    }

    override fun onActionTrigger(action: ViewAction?) {
        setState(oldViewState.copy(action = action))
        when (action) {
            is MainAction.LoginWithEmail -> loginWithEmail(action.email, action.password)
            MainAction.GetCountries -> getCountries()
        }
    }

    override fun clearState() {
        setState(MainState.initial())
    }
}