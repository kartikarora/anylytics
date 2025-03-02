package me.kartikarora.anylytics.models

import kotlinx.serialization.Serializable

/**
 * A sealed class representing the types of analytics events.
 *
 * This class defines the possible types of analytics events that can be
 * tracked in the application, providing a type-safe way to distinguish
 * between different categories of events.
 *
 * @property type String representation of the event type
 */
@Serializable
sealed class Type(val type: String) {
    /**
     * Represents a state-based event.
     *
     * State events typically track when a user views a screen or page,
     * or when the application enters a particular state.
     */
    @Serializable
    data object State : Type("state")

    /**
     * Represents an action-based event.
     *
     * Action events typically track when a user performs a specific action
     * such as clicking a button, submitting a form, or interacting with UI elements.
     */
    @Serializable
    data object Action : Type("action")
}