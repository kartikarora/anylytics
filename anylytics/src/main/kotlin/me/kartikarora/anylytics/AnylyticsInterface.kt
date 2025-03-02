package me.kartikarora.anylytics

import me.kartikarora.anylytics.models.Event

/**
 * The main interface for interacting with the Anylytics library.
 *
 * This interface defines methods for tracking different types of events, such as screen views and user actions.
 * Implement this interface to integrate Anylytics into your application.
 */
interface AnylyticsInterface {

    /**
     * Tracks a screen view event.
     *
     * This function is a convenience method that creates a [Event.View] event and tracks it.
     *
     * @param screenName The name of the screen being viewed.
     */
    fun trackScreen(screenName: String) {
        trackScreen(Event.View(screenName))
    }

    /**
     * Tracks a screen view event using a predefined [Event.View] object.
     *
     * @param view The [Event.View] object representing the screen view event.
     */
    fun trackScreen(view: Event.View)

    /**
     * Tracks a user action event.
     *
     * This function is a convenience method that creates a [Event.Action] event and tracks it.
     *
     * @param actionName The name of the action performed by the user.
     */
    fun trackAction(actionName: String) {
        trackAction(Event.Action(actionName))
    }

    /**
     * Tracks a user action event using a predefined [Event.Action] object.
     *
     * @param action The [Event.Action] object representing the user action event.
     */
    fun trackAction(action: Event.Action)
}