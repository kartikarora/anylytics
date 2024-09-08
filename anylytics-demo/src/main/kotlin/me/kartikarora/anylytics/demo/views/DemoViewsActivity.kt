package me.kartikarora.anylytics.demo.views

import android.app.UiModeManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.res.stringResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import me.kartikarora.anylytics.demo.R
import me.kartikarora.anylytics.demo.common.ACTION_BUTTON_TAP
import me.kartikarora.anylytics.demo.common.ACTION_MENU_ITEM_TAP
import me.kartikarora.anylytics.demo.common.BaseActivity
import me.kartikarora.anylytics.demo.common.DESTINATION_EXTERNAL
import me.kartikarora.anylytics.demo.common.DESTINATION_INTERNAL
import me.kartikarora.anylytics.demo.common.KEY_TAP_COUNT
import me.kartikarora.anylytics.demo.common.METHOD_INTENT
import me.kartikarora.anylytics.demo.common.METHOD_SHARE_SHEET
import me.kartikarora.anylytics.demo.common.METHOD_SNACKBAR
import me.kartikarora.anylytics.models.Event
import me.kartikarora.anylytics.demo.common.shareAnylyticsLibrary
import me.kartikarora.anylytics.demo.common.showSnackbar
import me.kartikarora.anylytics.demo.common.switchToCompose
import me.kartikarora.anylytics.demo.databinding.ActivityDemoViewsBinding
import me.kartikarora.anylytics.models.BreadCrumbs
import me.kartikarora.anylytics.models.ContextData

@AndroidEntryPoint
class DemoViewsActivity : BaseActivity() {

    private var tapCount = 0

    private lateinit var binding: ActivityDemoViewsBinding

    override val screenName: String
        get() = "Demo Views Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = ActivityDemoViewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firebaseAnylytics.trackScreen(Event.View(screenName))

        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_switch -> {
                    switchToCompose()
                    val event = Event.Action(
                        actionName = ACTION_MENU_ITEM_TAP,
                        contextData = ContextData(
                            screenName = screenName,
                            contextMap = mutableMapOf(
                                FirebaseAnalytics.Param.SCREEN_NAME to screenName,
                                FirebaseAnalytics.Param.DESTINATION to DESTINATION_INTERNAL,
                                FirebaseAnalytics.Param.METHOD to METHOD_INTENT
                            ),
                        ),
                        breadCrumbs = BreadCrumbs(
                            section = item.title.toString()
                        )
                    )
                    firebaseAnylytics.trackAction(event)
                }
            }
            true
        }

        binding.action1.text = getString(R.string.action_1, tapCount)
        binding.action1.setOnClickListener {
            ++tapCount
            val event = Event.Action(
                actionName = ACTION_BUTTON_TAP,
                contextData = ContextData(
                    screenName = screenName,
                    contextMap = mutableMapOf(
                        FirebaseAnalytics.Param.SCREEN_NAME to screenName,
                        KEY_TAP_COUNT to tapCount
                    )
                ),
                breadCrumbs = BreadCrumbs(
                    section = binding.action1.text as String
                )
            )
            firebaseAnylytics.trackAction(event)
            binding.action1.text = getString(R.string.action_1, tapCount)
        }

        binding.action2.setOnClickListener { button ->
            button.showSnackbar(ACTION_BUTTON_TAP)
            val event = Event.Action(
                actionName = ACTION_BUTTON_TAP,
                contextData = ContextData(
                    screenName = screenName,
                    contextMap = mutableMapOf(
                        FirebaseAnalytics.Param.SCREEN_NAME to screenName,
                        FirebaseAnalytics.Param.DESTINATION to DESTINATION_INTERNAL,
                        FirebaseAnalytics.Param.METHOD to METHOD_SNACKBAR
                    )
                ),
                breadCrumbs = BreadCrumbs(
                    section = binding.action2.text as String
                )
            )
            firebaseAnylytics.trackAction(event)
        }

        binding.action3.setOnClickListener {
            shareAnylyticsLibrary()
            val event = Event.Action(
                actionName = ACTION_BUTTON_TAP,
                contextData = ContextData(
                    screenName = screenName,
                    contextMap = mutableMapOf(
                        FirebaseAnalytics.Param.SCREEN_NAME to screenName,
                        FirebaseAnalytics.Param.DESTINATION to DESTINATION_EXTERNAL,
                        FirebaseAnalytics.Param.METHOD to METHOD_SHARE_SHEET
                    )
                ),
                breadCrumbs = BreadCrumbs(
                    section = binding.action3.text as String
                )
            )
            firebaseAnylytics.trackAction(event)
        }
    }
}