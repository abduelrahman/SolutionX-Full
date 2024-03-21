package am.leon.solutionx.common.presentation

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class SolutionXViewModel<Action : ViewAction, Event : ViewEvent, State : ViewState>(
    initialState: State
) : ISolutionXViewModel<Action, Event, State>, ViewModel() {

    private val _viewState: MutableStateFlow<State> = MutableStateFlow(initialState)
    override val viewState: StateFlow<State>
        get() = _viewState

    val oldViewState: State
        get() = viewState.value

    private val eventChannel = Channel<Event>(Channel.UNLIMITED)
    final override val singleEvent: Flow<Event> = eventChannel.receiveAsFlow()

    private val _actionMutableFlow = MutableSharedFlow<Action>(extraBufferCapacity = Int.MAX_VALUE)
    final override fun processIntent(action: Action) {
        check(_actionMutableFlow.tryEmit(action)) { "Failed to emit action: $action" }
    }

    /**
     * Must be called in [kotlinx.coroutines.Dispatchers.Main],
     * otherwise it will throw an exception.
     *
     * If you want to send an event from other [kotlinx.coroutines.CoroutineDispatcher],
     * use `withContext(Dispatchers.Main.immediate) { sendEvent(event) }`.
     */
    protected fun sendEvent(event: Event) {
        println("Current event: $event")
        eventChannel.trySend(event)
    }

    fun setState(newState: State) {
        _viewState.value = newState
        println("Current state: ${viewState.value}")
    }

    abstract fun clearState()

    private val actionSharedFlow: SharedFlow<Action>
        get() = _actionMutableFlow

    abstract fun onActionTrigger(action: ViewAction?)

    init {
        viewModelScope.launch {
            actionSharedFlow.collect {
                onActionTrigger(it)
            }
        }
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        eventChannel.close()
    }
}