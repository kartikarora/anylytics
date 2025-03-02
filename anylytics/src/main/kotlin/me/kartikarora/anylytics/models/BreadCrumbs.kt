package me.kartikarora.anylytics.models

import me.kartikarora.anylytics.Constants.NO_VALUE
import kotlinx.serialization.Serializable

/**
 * Data class that represents breadcrumb navigation information.
 *
 * Breadcrumbs provide hierarchical navigation context in the UI, showing the user's
 * current location within the application's navigation structure.
 *
 * @property section The top-level section name, defaults to [NO_VALUE] if not specified
 * @property subSection The second-level section name, defaults to [NO_VALUE] if not specified
 * @property subSubSection The third-level section name, defaults to [NO_VALUE] if not specified
 */
@Serializable
data class BreadCrumbs(
    val section: String = NO_VALUE,
    val subSection: String = NO_VALUE,
    val subSubSection: String = NO_VALUE
) {
    /**
     * Checks if all breadcrumb sections are empty.
     *
     * @return `true` if all sections are empty strings, `false` otherwise
     */
    fun areEmpty() = section.isEmpty() && subSection.isEmpty() && subSubSection.isEmpty()

    /**
     * Checks if any breadcrumb section has a value.
     *
     * @return `true` if at least one section has a non-empty value, `false` if all are empty
     */
    fun areNotEmpty() = section.isNotEmpty() || subSection.isNotEmpty() || subSubSection.isNotEmpty()
}