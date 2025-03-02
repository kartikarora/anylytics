package me.kartikarora.anylytics.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import me.kartikarora.anylytics.Constants.KEY_SECTION
import me.kartikarora.anylytics.Constants.KEY_SUB_SECTION
import me.kartikarora.anylytics.Constants.KEY_SUB_SUB_SECTION
import me.kartikarora.anylytics.Constants.NO_VALUE

/**
 * Serializable class that manages contextual data for analytics tracking.
 *
 * ContextData stores information about the current screen and additional
 * contextual details like navigation breadcrumbs that can be used for
 * analytics purposes.
 *
 * @property screenName The name of the current screen, defaults to [NO_VALUE] if not specified
 * @property contextMap Mutable map that stores various contextual properties with their values
 */
@Serializable
class ContextData(
    private val screenName: String = NO_VALUE,
    private val contextMap: MutableMap<String, @Contextual Any?> = mutableMapOf()
) {
    /**
     * Sets breadcrumb navigation information in the context map.
     *
     * This method extracts non-empty values from the [BreadCrumbs] object and
     * adds them to the context map using predefined keys from Constants.
     *
     * @param breadCrumbs The [BreadCrumbs] object containing navigation hierarchy information
     */
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

    /**
     * Returns an immutable copy of the context map.
     *
     * @return An immutable Map containing all the context data
     */
    fun getMap(): Map<String, Any?> = contextMap.toMap()
}