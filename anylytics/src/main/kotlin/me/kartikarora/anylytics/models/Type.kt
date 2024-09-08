package me.kartikarora.anylytics.models

import kotlinx.serialization.Serializable

@Serializable
sealed class Type(val type: String) {
    @Serializable
    data object State : Type("state")

    @Serializable
    data object Action : Type("action")
}