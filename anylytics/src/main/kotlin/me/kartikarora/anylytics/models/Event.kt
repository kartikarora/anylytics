package me.kartikarora.anylytics.models

import android.os.Bundle
import kotlinx.serialization.Serializable

/**
 * A sealed class representing analytics events in the application.
 *
 * This class serves as the base for different types of analytics events
 * that can be tracked throughout the application.
 *
 * @property analyticsType The type of analytics event (State or Action)
 */
@Serializable
sealed class Event(
    val analyticsType: Type
) {
    /**
     * Context data associated with this event
     */
    abstract val contextData: ContextData

    /**
     * Breadcrumb navigation data associated with this event
     */
    abstract val breadCrumbs: BreadCrumbs

    /**
     * Represents a view/screen event for analytics tracking.
     *
     * This event type is triggered when a user views a screen or page
     * in the application.
     *
     * @property screenName Name of the screen being viewed
     * @property contextData Additional context data for this view event,
     *                      defaults to a new [ContextData] with the screen name
     * @property breadCrumbs Navigation breadcrumbs for this view,
     *                      defaults to empty [BreadCrumbs]
     * @throws IllegalArgumentException if [screenName] is empty
     */
    @Serializable
    data class View(
        val screenName: String,
        override val contextData: ContextData = ContextData(screenName),
        override val breadCrumbs: BreadCrumbs = BreadCrumbs()
    ) : Event(analyticsType = Type.State) {
        init {
            if (screenName.isEmpty()) {
                throw IllegalArgumentException("Page name cannot be empty")
            }
        }
    }

    /**
     * Represents a user action event for analytics tracking.
     *
     * This event type is triggered when a user performs an action
     * such as clicking a button or submitting a form.
     *
     * @property actionName Name of the action being performed
     * @property contextData Additional context data for this action event,
     *                      defaults to a new empty [ContextData]
     * @property breadCrumbs Navigation breadcrumbs for this action,
     *                      defaults to empty [BreadCrumbs]
     * @throws IllegalArgumentException if [actionName] is empty
     */
    @Serializable
    data class Action(
        val actionName: String,
        override val contextData: ContextData = ContextData(),
        override val breadCrumbs: BreadCrumbs = BreadCrumbs()
    ) : Event(analyticsType = Type.Action) {
        init {
            if (actionName.isEmpty()) {
                throw IllegalArgumentException("Action name cannot be empty")
            }
        }
    }
}