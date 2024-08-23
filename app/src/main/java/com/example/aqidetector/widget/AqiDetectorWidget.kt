package com.example.aqidetector.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.text.Text

class AqiDetectorWidget: GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // load data needed to render the AppWidget
        // use `withContext` to switch to another thread for long running operations

        provideContent {
            // create AppWidget here
            Text(text = "Hello, World!")
        }
    }
}