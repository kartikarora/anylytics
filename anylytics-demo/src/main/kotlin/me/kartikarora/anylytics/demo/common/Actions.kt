package me.kartikarora.anylytics.demo.common

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.activity.ComponentActivity
import com.google.android.material.snackbar.Snackbar
import me.kartikarora.anylytics.demo.compose.DemoComposeActivity
import me.kartikarora.anylytics.demo.views.DemoViewsActivity

fun Context.shareAnylyticsLibrary() {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun ComponentActivity.switchToCompose() {
    startActivity(
        Intent(
            this,
            DemoComposeActivity::class.java
        )
    )
    this.finish()
}

fun ComponentActivity.switchToViews() {
    startActivity(
        Intent(
            this,
            DemoViewsActivity::class.java
        )
    )
    this.finish()
}