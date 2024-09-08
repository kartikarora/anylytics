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

class AnylyticsFirebase @Inject constructor() : AnylyticsInterface {

    private val analytics = Firebase.analytics

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

    override fun trackScreen(view: Event.View) {
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, view.screenName)
            view.toParams()
        }
    }

    override fun trackAction(action: Event.Action) {
        val actionName = action.actionName.replace(" ", "_").lowercase()
        analytics.logEvent(actionName, action.toParams())
    }
}