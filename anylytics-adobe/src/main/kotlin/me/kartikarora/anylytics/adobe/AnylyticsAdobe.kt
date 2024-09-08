package me.kartikarora.anylytics.adobe

import com.adobe.marketing.mobile.MobileCore
import me.kartikarora.anylytics.AnylyticsInterface
import me.kartikarora.anylytics.models.Event
import javax.inject.Inject

class AnylyticsAdobe @Inject constructor() : AnylyticsInterface {

    private fun Event.toContextMap(): Map<String, String> {
        contextData.setBreadcrumbs(breadCrumbs)
        return contextData.getMap().mapValues { entry ->
            entry.value.toString()
        }
    }

    override fun trackScreen(view: Event.View) {
        MobileCore.trackState(view.screenName, view.toContextMap())
    }

    override fun trackAction(action: Event.Action) {
        MobileCore.trackAction(action.actionName, action.toContextMap())
    }
}