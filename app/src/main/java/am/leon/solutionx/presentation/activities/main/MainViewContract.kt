package am.leon.solutionx.presentation.activities.main

import am.leon.solutionx.common.data.models.exception.LeonException
import am.leon.solutionx.common.presentation.ViewAction
import am.leon.solutionx.common.presentation.ViewEvent
import am.leon.solutionx.common.presentation.ViewState
import am.leon.solutionx.features.services.country.domain.models.Country

interface MainViewContract {
    sealed class MainAction : ViewAction {
        data object GetCountries : MainAction()
        data class LoginWithEmail(val email: String, val password: String) : MainAction()
    }

    sealed class MainEvent : ViewEvent {
        data class ValidationError(val errors: Map<String, String>) : MainEvent()
        data class CountriesIndex(val countries: List<Country>) : MainEvent()
        data class LoginIsSuccessfully(val message: String) : MainEvent()
        // Navigate to Signup
    }

    data class MainState(
        val isLoading: Boolean, val exception: LeonException?, val action: ViewAction?
    ) : ViewState {
        companion object {
            fun initial() = MainState(
                isLoading = false, exception = null, action = null
            )
        }
    }
}