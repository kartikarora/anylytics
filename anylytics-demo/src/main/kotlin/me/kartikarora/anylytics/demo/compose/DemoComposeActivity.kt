package me.kartikarora.anylytics.demo.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import me.kartikarora.anylytics.compose.LocalAnylyticsInterface
import me.kartikarora.anylytics.compose.TrackAction
import me.kartikarora.anylytics.compose.TrackScreen
import me.kartikarora.anylytics.demo.common.ACTION_MENU_ITEM_TAP
import me.kartikarora.anylytics.demo.common.BaseActivity
import me.kartikarora.anylytics.demo.common.DESTINATION_INTERNAL
import me.kartikarora.anylytics.demo.common.METHOD_INTENT
import me.kartikarora.anylytics.demo.common.switchToCompose
import me.kartikarora.anylytics.demo.common.switchToViews
import me.kartikarora.anylytics.demo.compose.ui.composables.MainScreen
import me.kartikarora.anylytics.demo.compose.ui.theme.AnylyticsTheme
import me.kartikarora.anylytics.models.BreadCrumbs
import me.kartikarora.anylytics.models.ContextData
import me.kartikarora.anylytics.models.Event

@AndroidEntryPoint
class DemoComposeActivity : BaseActivity() {
    override val screenName: String
        get() = "Demo Compose Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(
                LocalAnylyticsInterface provides firebaseAnylytics
            ) {
                TrackScreen(screenName)
                AnylyticsTheme {
                    MainScreen(screenName) { actionText ->
                        switchToViews()
                        val event = Event.Action(
                            actionName = ACTION_MENU_ITEM_TAP,
                            contextData = ContextData(
                                screenName = screenName,
                                contextMap = mutableMapOf(
                                    FirebaseAnalytics.Param.SCREEN_NAME to screenName,
                                    FirebaseAnalytics.Param.DESTINATION to DESTINATION_INTERNAL,
                                    FirebaseAnalytics.Param.METHOD to METHOD_INTENT
                                )
                            ),
                            breadCrumbs = BreadCrumbs(
                                section = actionText
                            )
                        )
                        firebaseAnylytics.trackAction(event)
                    }
                }
            }
        }
    }
}