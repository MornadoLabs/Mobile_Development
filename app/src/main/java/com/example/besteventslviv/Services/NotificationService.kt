package com.example.besteventslviv.Services

import android.app.*
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.example.besteventslviv.Activities.DayEventsActivity
import com.example.besteventslviv.Activities.EventInfoActivity
import com.example.besteventslviv.Activities.EventsActivity
import com.example.besteventslviv.Activities.GroupsActivity
import com.example.besteventslviv.Helpers.DateHelper
import com.example.besteventslviv.R
import java.util.*
import java.util.concurrent.TimeUnit

class NotificationService : IntentService("NotificationService") {

    private var eventID: Int = 0
    private var eventName: String? = ""
    private var eventLocation: String? = ""
    private var eventDate: Date? = null

    override fun onHandleIntent(intent: Intent?) {
        val extra = intent?.extras
        if (!extra?.isEmpty!!) {
            eventID = extra.getInt("event_id")
            eventName = extra.getString("event_name")
            eventLocation = extra.getString("event_location")
            eventDate = extra.getSerializable("event_date") as Date
        }

        try {
            TimeUnit.SECONDS.sleep(5)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(eventID, buildNotification(createPendingIntent()))

        stopSelf()
    }

    private fun createPendingIntent(): PendingIntent {

        val dayEventsIntent = Intent(this, DayEventsActivity::class.java)

        val eventInfoIntent = Intent(this, EventInfoActivity::class.java)

        val eventsIntent = Intent(this, EventsActivity::class.java)

        val groupsIntent = Intent(this, GroupsActivity::class.java)

        val taskStackBuilder = TaskStackBuilder.create(this)
        taskStackBuilder.addNextIntent(groupsIntent)
        taskStackBuilder.addNextIntent(eventsIntent)
        taskStackBuilder.addNextIntent(eventInfoIntent)
        taskStackBuilder.addNextIntent(dayEventsIntent)

        return taskStackBuilder.getPendingIntent(eventID, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun buildNotification(pendingIntent: PendingIntent): Notification {
        val text = "$eventName was added to your events."
        val summary = "$eventName will start at ${DateHelper.DateToTimeString(eventDate!!)} in $eventLocation. Don't miss it!"

        return NotificationCompat.Builder(this, "MyChannel")
            .setSmallIcon(R.mipmap.ic_launcher_foreground)
            .setContentTitle("Your event is coming!")
            .setContentText("Don't miss $eventName")
            .setStyle(
                NotificationCompat.InboxStyle()
                    .addLine(text).setSummaryText(summary))
            .setShowWhen(true)
            .setContentIntent(pendingIntent)
            .build()
    }

}