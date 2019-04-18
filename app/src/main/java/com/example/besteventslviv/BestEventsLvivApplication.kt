package com.example.besteventslviv

import android.app.Application
import org.acra.ACRA
import org.acra.ReportingInteractionMode
import org.acra.annotation.ReportsCrashes

@ReportsCrashes(
    mailTo = "pavlinm2@gmail.com",
    mode = ReportingInteractionMode.TOAST,
    resToastText = R.string.unknown_error)
class BestEventsLvivApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ACRA.init(this)
    }
}