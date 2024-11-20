package me.kartikarora.anylytics.compose

import androidx.compose.runtime.staticCompositionLocalOf
import me.kartikarora.anylytics.AnylyticsInterface
import me.kartikarora.anylytics.NoOpAnylyticsImpl

/**
 * Global key used to obtain access to the Anylytics Interface
 * through a CompositionLocal. Using this composition, without providing an implementation will result in an error.
 */
val LocalAnylyticsInterface = staticCompositionLocalOf<AnylyticsInterface> {
    error("LocalAnylyticsInterface not implemented")
}
