package me.kartikarora.anylytics.demo.compose.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.analytics.FirebaseAnalytics
import me.kartikarora.anylytics.compose.LocalAnylyticsInterface
import me.kartikarora.anylytics.demo.R
import me.kartikarora.anylytics.demo.common.ACTION_BUTTON_TAP
import me.kartikarora.anylytics.demo.common.DESTINATION_EXTERNAL
import me.kartikarora.anylytics.demo.common.DESTINATION_INTERNAL
import me.kartikarora.anylytics.demo.common.KEY_TAP_COUNT
import me.kartikarora.anylytics.demo.common.METHOD_SHARE_SHEET
import me.kartikarora.anylytics.demo.common.METHOD_SNACKBAR
import me.kartikarora.anylytics.demo.common.shareAnylyticsLibrary
import me.kartikarora.anylytics.demo.compose.ui.theme.AnylyticsTheme
import me.kartikarora.anylytics.models.BreadCrumbs
import me.kartikarora.anylytics.models.ContextData
import me.kartikarora.anylytics.models.Event

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    screenName: String,
    onActionClicked: (actionText: String) -> Unit = {}
) {
    var tapCount by remember { mutableIntStateOf(0) }
    var showSnackbar by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val firebaseAnylytics = LocalAnylyticsInterface.current
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.compose))
                },
                actions = {
                    val menuItemText = stringResource(R.string.switch_activity)
                    IconButton(onClick = {
                        onActionClicked.invoke(menuItemText)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_switch_to_views),
                            contentDescription = menuItemText
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = stringResource(R.string.anylytics_demo),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
                )
                val button1Text = stringResource(R.string.action_1, tapCount)
                Button(onClick = {
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
                            section = button1Text
                        )
                    )
                    firebaseAnylytics.trackAction(event)
                }) {
                    Text(button1Text)
                }

                Spacer(modifier = Modifier.height(8.dp))
                val button2Text = stringResource(R.string.action_2)
                Button(onClick = {
                    showSnackbar = true
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
                            section = button2Text
                        )
                    )
                    firebaseAnylytics.trackAction(event)
                }) {
                    if (showSnackbar) {
                        LaunchedEffect(snackbarHostState) {
                            snackbarHostState.showSnackbar("Action 2 Tapped")
                        }
                    }
                    Text(button2Text)
                }

                Spacer(modifier = Modifier.height(8.dp))
                val button3Text = stringResource(R.string.action_3)
                Button(onClick = {
                    context.shareAnylyticsLibrary()
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
                            section = button3Text
                        )
                    )
                    firebaseAnylytics.trackAction(event)
                }) {
                    Icon(
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        painter = painterResource(R.drawable.ic_share),
                        contentDescription = stringResource(R.string.switch_activity)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(button3Text)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AnylyticsTheme {
        MainScreen("")
    }
}