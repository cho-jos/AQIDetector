package com.example.aqidetector.glance

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.example.aqidetector.widget.AqiDetectorWidget

class AqiDetectorWidgetReceiver: GlanceAppWidgetReceiver() {
    // Let GlanceAppWidgetReceiver know which GlanceAppWidget to use
    override val glanceAppWidget: GlanceAppWidget = AqiDetectorWidget()
}