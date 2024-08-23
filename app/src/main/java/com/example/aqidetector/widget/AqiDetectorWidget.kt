package com.example.aqidetector.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.text.Text

class AqiDetectorWidget: GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // load data needed to render the AppWidget
        // use `withContext` to switch to another thread for long running operations

        provideContent {
            // create AppWidget here
            WidgetContent()
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview(widthDp = 100, heightDp = 100)
@Composable
private fun WidgetContent() {
    Text(text = "Hello, World")
}