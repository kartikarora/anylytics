package me.kartikarora.anylytics.adobe

import com.adobe.marketing.mobile.MobileCore
import me.kartikarora.anylytics.AnylyticsInterface
import me.kartikarora.anylytics.models.Event
import javax.inject.Inject

/**
 * Adobe Analytics implementation of the [AnylyticsInterface].
 *
 * This class handles sending analytics events to Adobe Experience Platform SDK,
 * including screen view tracking and user action tracking.
 */
class AnylyticsAdobe @Inject constructor() : AnylyticsInterface {

    /**
     * Converts an [Event] to a map of context data compatible with Adobe Analytics.
     *
     * This function processes the event's context data and breadcrumbs,
     * converting all values to strings as required by Adobe's MobileCore.
     *
     * @return A [Map] of string key-value pairs containing all context data from the event
     */
    private fun Event.toContextMap(): Map<String, String> {
        contextData.setBreadcrumbs(breadCrumbs)
        return contextData.getMap().mapValues { entry ->
            entry.value.toString()
        }
    }

    /**
     * Tracks a screen view event in Adobe Analytics.
     *
     * This implementation maps to Adobe's trackState method, which is used
     * for screen view or state change tracking.
     *
     * @param view The [Event.View] object containing screen information and context
     */
    override fun trackScreen(view: Event.View) {
        MobileCore.trackState(view.screenName, view.toContextMap())
    }

    /**
     * Tracks a user action event in Adobe Analytics.
     *
     * This implementation maps to Adobe's trackAction method, which is used
     * for user interaction and action tracking.
     *
     * @param action The [Event.Action] object containing action information and context
     */
    override fun trackAction(action: Event.Action) {
        MobileCore.trackAction(action.actionName, action.toContextMap())
    }
}