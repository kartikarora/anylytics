package me.kartikarora.anylytics.models

import me.kartikarora.anylytics.Constants.NO_VALUE
import kotlinx.serialization.Serializable

@Serializable
    data class BreadCrumbs(
        val section: String = NO_VALUE,
        val subSection: String = NO_VALUE,
        val subSubSection: String = NO_VALUE
    ) {
        fun areEmpty() = section.isEmpty() && subSection.isEmpty() && subSubSection.isEmpty()
        fun areNotEmpty() = section.isNotEmpty() || subSection.isNotEmpty() || subSubSection.isNotEmpty()
    }