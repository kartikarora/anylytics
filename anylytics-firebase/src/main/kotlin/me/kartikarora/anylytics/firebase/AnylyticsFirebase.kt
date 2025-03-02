package me.kartikarora.anylytics.firebase

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ParametersBuilder
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.ktx.Firebase
import me.kartikarora.anylytics.AnylyticsInterface
import me.kartikarora.anylytics.models.Event
import javax.inject.Inject

/**
 * Firebase implementation of the [AnylyticsInterface].
 *
 * This class handles sending analytics events to Firebase Analytics,
 * including screen view tracking and user action tracking.
 */
class AnylyticsFirebase @Inject constructor() : AnylyticsInterface {

    /**
     * The Firebase Analytics instance used for event logging.
     */
    private val analytics = Firebase.analytics

    /**
     * Converts an [Event] to a Firebase-compatible [Bundle] of parameters.
     *
     * This function processes the event's context data and breadcrumbs,
     * converting them to appropriate parameter types for Firebase Analytics.
     *
     * @return A [Bundle] containing all parameters from the event's context
     */
    private fun Event.toParams(): Bundle {
        contextData.setBreadcrumbs(breadCrumbs)
        return Bundle().apply {
            contextData.getMap().forEach { (key, value) ->
                when(value){
                    is Boolean -> putBoolean(key, value)
                    is Bundle -> putBundle(key, value)
                    is Byte -> putByte(key, value)
                    is Char -> putChar(key, value)
                    is Double -> putDouble(key, value)
                    is Float -> putFloat(key, value)
                    is Int -> putInt(key, value)
                    is Long -> putLong(key, value)
                    is Short -> putShort(key, value)
                    is String -> putString(key, value)
                    else -> putString(key, value.toString())
                }
            }
        }
    }

    /**
     * Tracks a screen view event in Firebase Analytics.
     *
     * This implementation logs a standard [FirebaseAnalytics.Event.SCREEN_VIEW] event
     * with the screen name and any additional context parameters.
     *
     * @param view The [Event.View] object containing screen information and context
     */
    override fun trackScreen(view: Event.View) {
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, view.screenName)
            view.toParams()
        }
    }

    /**
     * Tracks a user action event in Firebase Analytics.
     *
     * This implementation logs a custom event with the action name (converted to
     * snake_case format as per Firebase recommendations) and any additional
     * context parameters.
     *
     * @param action The [Event.Action] object containing action information and context
     */
    override fun trackAction(action: Event.Action) {
        val actionName = action.actionName.replace(" ", "_").lowercase()
        analytics.logEvent(actionName, action.toParams())
    }
}