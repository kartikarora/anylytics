package me.kartikarora.anylytics.demo.common

import androidx.activity.ComponentActivity
import me.kartikarora.anylytics.firebase.AnylyticsFirebase
import javax.inject.Inject

abstract class BaseActivity : ComponentActivity() {

    abstract val screenName: String

    @Inject
    lateinit var firebaseAnylytics: AnylyticsFirebase
}