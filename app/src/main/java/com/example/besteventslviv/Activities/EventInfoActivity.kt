package com.example.besteventslviv.Activities

import android.graphics.BitmapFactory
import android.os.Bundle
import com.example.besteventslviv.Database.AppDatabase
import com.example.besteventslviv.Helpers.DateHelper
import com.example.besteventslviv.R
import com.example.besteventslviv.StaticCache
import kotlinx.android.synthetic.main.activity_event_info.*
import android.content.Intent
import android.net.Uri
import com.example.besteventslviv.Database.Entities.Event
import com.example.besteventslviv.Database.Entities.UserEvent
import com.example.besteventslviv.Services.NotificationService


class EventInfoActivity : BaseActivity() {

    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_info)
    }

    override fun onStart() {
        super.onStart()

        appDatabase = AppDatabase.getAppDatabase(this.baseContext)!!
        val eventsDao = appDatabase.getEventsDao()
        val currentEvent = eventsDao.getEventById(StaticCache.EventID!!)

        event_info_id.text = StaticCache.EventID.toString()
        event_info_image.setImageBitmap(BitmapFactory.decodeByteArray(currentEvent.Image, 0, currentEvent.Image.size))
        event_info_header.text = currentEvent.Name
        event_info_date.text = DateHelper.DateToString(currentEvent.Date)
        event_info_location.text = currentEvent.Location
        event_info_price.text = currentEvent.TicketsPrice
        event_info_description.text = currentEvent.Description

        event_info_get_tickets.setOnClickListener { _ -> processGetTickets() }
    }

    private fun processGetTickets() {
        val eventsDao = appDatabase.getEventsDao()
        val userEventsDao = appDatabase.getUserEventsDao()

        val eventId = event_info_id.text.toString().toInt()
        val event = eventsDao.getEventById(eventId)

        if (userEventsDao.getUserEventByUserIDAndEventID(StaticCache.UserID!!, eventId) == null){
            val userEvent = UserEvent(StaticCache.UserID!!, eventId, true)
            userEventsDao.Insert(userEvent)

            startService(Intent(this, NotificationService::class.java)
                .putExtra("event_id", event.ID)
                .putExtra("event_name", event.Name)
                .putExtra("event_location", event.Location)
                .putExtra("event_date", event.Date))
        }

        val link = event.Link
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}
