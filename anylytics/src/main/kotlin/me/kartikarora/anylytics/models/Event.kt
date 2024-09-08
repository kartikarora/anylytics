package me.kartikarora.anylytics.models

import android.os.Bundle
import kotlinx.serialization.Serializable

@Serializable
sealed class Event(
    val analyticsType: Type
) {
    abstract val contextData: ContextData
    abstract val breadCrumbs: BreadCrumbs

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