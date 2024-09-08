package me.kartikarora.anylytics.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import me.kartikarora.anylytics.Constants.KEY_SECTION
import me.kartikarora.anylytics.Constants.KEY_SUB_SECTION
import me.kartikarora.anylytics.Constants.KEY_SUB_SUB_SECTION
import me.kartikarora.anylytics.Constants.NO_VALUE

@Serializable
class ContextData(
    private val screenName: String = NO_VALUE,
    private val contextMap: MutableMap<String, @Contextual Any?> = mutableMapOf()
) {
    fun setBreadcrumbs(breadCrumbs: BreadCrumbs) {
        if (breadCrumbs.areNotEmpty()) {
            with(breadCrumbs) {
                if (section.isNotEmpty()) {
                    contextMap[KEY_SECTION] = section
                }
                if (subSection.isNotEmpty()) {
                    contextMap[KEY_SUB_SECTION] = subSection
                }
                if (subSubSection.isNotEmpty()) {
                    contextMap[KEY_SUB_SUB_SECTION] = subSubSection
                }
            }
        }
    }

    fun getMap(): Map<String, Any?> = contextMap.toMap()
}