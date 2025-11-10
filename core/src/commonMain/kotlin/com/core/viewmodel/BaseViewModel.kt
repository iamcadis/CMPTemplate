package com.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.ExperimentalAtomicApi

/**
 * Base ViewModel implementing MVI (Model-View-Intent) architecture pattern.
 *
 * Initial data is loaded lazily when the state flow is first collected,
 * using onStart + stateIn pattern for optimal performance and testability.
 *
 * @param S State type representing UI state
 * @param A Action type representing user intentions
 * @param E Effect type representing one-time events
 * @param initialState The initial state of the ViewModel
 */
@OptIn(ExperimentalAtomicApi::class)
abstract class BaseViewModel<S : State, A : Action, E : Effect>(
    initialState: S
) : ViewModel() {

    // Thread-safe flag to ensure initial data loads only once per collector lifecycle
    private val hasInitialDataLoaded = AtomicBoolean(false)

    // Stores the last failed operation for retry capability
    private var pendingRetryAction: (() -> Unit)? = null

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state
        .onStart {
            // Load initial data only once when flow starts being collected
            if (hasInitialDataLoaded.compareAndSet(false, true)) {
                loadInitialData()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = initialState
        )

    private val _effect = Channel<E>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    private val _error = Channel<Throwable>(Channel.BUFFERED)
    val error = _error.receiveAsFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        sendError(exception)
    }

    /**
     * Override to load initial data when state flow is first collected.
     * Called automatically only once when UI starts observing the state.
     *
     * This approach provides:
     * - Better testability (can control when data loads)
     * - Performance optimization (loads only when needed)
     * - Proper lifecycle awareness
     */
    protected open fun loadInitialData() {}

    /**
     * Handles user actions/intents.
     * Override this to implement action handling logic.
     *
     * @param action The action to handle
     */
    open fun handleAction(action: A) {}

    /**
     * Launches a coroutine with error handling and optional retry capability.
     * Exceptions are automatically caught and sent to the error channel.
     *
     * @param onRetry Optional callback to store for retry functionality.
     *                When provided, this action can be re-executed via retry().
     *                Previous retry actions are automatically replaced.
     * @param block The suspending code block to execute
     */
    protected fun launchSafe(
        onRetry: (() -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(exceptionHandler) {
            // Store retry action if provided (replaces previous one)
            onRetry?.let { pendingRetryAction = it }
            block()
        }
    }

    /**
     * Updates the current state using the provided transformation function.
     *
     * @param transform Function that receives current state and returns new state
     */
    protected fun updateState(transform: S.() -> S) {
        _state.update(transform)
    }

    /**
     * Sends a one-time effect to the UI layer.
     *
     * @param effect The effect to send
     */
    protected fun sendEffect(effect: E) {
        _effect.trySend(effect)
    }

    /**
     * Sends an error to the error channel.
     * Can be overridden to implement custom error handling logic.
     *
     * @param error The error to handle
     */
    protected open fun sendError(error: Throwable) {
        _error.trySend(error)
    }

    /**
     * Gets the current state value synchronously.
     */
    protected val currentState: S
        get() = _state.value

    /**
     * Retries the last failed operation if a retry action was provided.
     * Call this method when user wants to retry after a connection error.
     *
     * @return true if retry action was executed, false if no retry action available
     */
    fun retry(): Boolean {
        return pendingRetryAction?.let { action ->
            action()
            pendingRetryAction = null
            true
        } ?: false
    }

    override fun onCleared() {
        super.onCleared()
        // Clear retry action
        pendingRetryAction = null
        // Close channels to prevent memory leaks
        _effect.close()
        _error.close()
    }
}