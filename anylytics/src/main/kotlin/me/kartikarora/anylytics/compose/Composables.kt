package me.kartikarora.anylytics.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import me.kartikarora.anylytics.AnylyticsInterface
import me.kartikarora.anylytics.models.Event

@Composable
fun TrackScreen(
    view: Event.View,
    analyticsInterface: AnylyticsInterface = LocalAnylyticsInterface.current
) = LaunchedEffect(view.screenName) { analyticsInterface.trackScreen(view) }

@Composable
fun TrackScreen(
    screenName: String,
    analyticsInterface: AnylyticsInterface = LocalAnylyticsInterface.current
) = LaunchedEffect(screenName) { analyticsInterface.trackScreen(screenName) }

@Composable
fun TrackAction(
    analyticsAction: Event.Action,
    analyticsInterface: AnylyticsInterface = LocalAnylyticsInterface.current
) = LaunchedEffect(Unit) { analyticsInterface.trackAction(analyticsAction) }

@Composable
fun TrackAction(
    actionName: String,
    analyticsInterface: AnylyticsInterface = LocalAnylyticsInterface.current
) = LaunchedEffect(Unit) { analyticsInterface.trackAction(actionName) }