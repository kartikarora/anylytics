package me.kartikarora.anylytics

import me.kartikarora.anylytics.models.Event

interface AnylyticsInterface {
    fun trackScreen(screenName: String) {
        trackScreen(Event.View(screenName))
    }

    fun trackScreen(view: Event.View)

    fun trackAction(actionName: String) {
        trackAction(Event.Action(actionName))
    }

    fun trackAction(action: Event.Action)
}