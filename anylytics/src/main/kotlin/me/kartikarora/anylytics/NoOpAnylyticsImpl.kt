package me.kartikarora.anylytics

import me.kartikarora.anylytics.models.Event

class NoOpAnylyticsImpl : AnylyticsInterface {
    override fun trackScreen(view: Event.View) = Unit

    override fun trackAction(action: Event.Action) = Unit
}
