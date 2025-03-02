package me.kartikarora.anylytics.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import me.kartikarora.anylytics.AnylyticsInterface
import me.kartikarora.anylytics.models.Event

/**
 * A Composable function that tracks screen views using an [Event.View] object.
 *
 * This function triggers analytics tracking when a screen is viewed, with the tracking
 * being re-triggered whenever the screen name changes.
 *
 * @param view The [Event.View] object containing screen name and additional context
 * @param analyticsInterface The analytics interface to use for tracking, defaults to the current
 *                          [LocalAnylyticsInterface]
 */
@Composable
fun TrackScreen(
    view: Event.View,
    analyticsInterface: AnylyticsInterface = LocalAnylyticsInterface.current
) = LaunchedEffect(view.screenName) { analyticsInterface.trackScreen(view) }

/**
 * A Composable function that tracks screen views using just a screen name.
 *
 * This function provides a simplified way to track screen views when no additional
 * context data is needed.
 *
 * @param screenName The name of the screen being viewed
 * @param analyticsInterface The analytics interface to use for tracking, defaults to the current
 *                          [LocalAnylyticsInterface]
 */
@Composable
fun TrackScreen(
    screenName: String,
    analyticsInterface: AnylyticsInterface = LocalAnylyticsInterface.current
) = LaunchedEffect(screenName) { analyticsInterface.trackScreen(screenName) }

/**
 * A Composable function that tracks user actions using an [Event.Action] object.
 *
 * This function triggers analytics tracking when a user performs an action,
 * using a complete [Event.Action] object with context data.
 *
 * @param analyticsAction The [Event.Action] object containing action name and additional context
 * @param analyticsInterface The analytics interface to use for tracking, defaults to the current
 *                          [LocalAnylyticsInterface]
 */
@Composable
fun TrackAction(
    analyticsAction: Event.Action,
    analyticsInterface: AnylyticsInterface = LocalAnylyticsInterface.current
) = LaunchedEffect(Unit) { analyticsInterface.trackAction(analyticsAction) }

/**
 * A Composable function that tracks user actions using just an action name.
 *
 * This function provides a simplified way to track user actions when no additional
 * context data is needed.
 *
 * @param actionName The name of the action being performed
 * @param analyticsInterface The analytics interface to use for tracking, defaults to the current
 *                          [LocalAnylyticsInterface]
 */
@Composable
fun TrackAction(
    actionName: String,
    analyticsInterface: AnylyticsInterface = LocalAnylyticsInterface.current
) = LaunchedEffect(Unit) { analyticsInterface.trackAction(actionName) }