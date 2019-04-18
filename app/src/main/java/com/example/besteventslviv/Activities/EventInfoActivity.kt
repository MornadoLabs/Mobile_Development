package com.example.besteventslviv.Activities

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.besteventslviv.Database.AppDatabase
import com.example.besteventslviv.Helpers.DateHelper
import com.example.besteventslviv.R
import com.example.besteventslviv.StaticCache
import kotlinx.android.synthetic.main.activity_event_info.*
import android.content.Intent
import android.net.Uri


class EventInfoActivity : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_info)
    }

    override fun onStart() {
        super.onStart()

        appDatabase = AppDatabase.getAppDatabase(this.baseContext)!!
        var eventsDao = appDatabase.getEventsDao()
        var currentEvent = eventsDao.getEventById(StaticCache.EventID!!)

        event_info_id.text = StaticCache.EventID.toString()
        event_info_image.setImageBitmap(BitmapFactory.decodeByteArray(currentEvent.Image, 0, currentEvent.Image.size))
        event_info_header.text = currentEvent.Name
        event_info_date.text = DateHelper.DateToString(currentEvent.Date)
        event_info_location.text = currentEvent.Location
        event_info_price.text = currentEvent.TicketsPrice
        event_info_description.text = currentEvent.Description

        event_info_get_tickets.setOnClickListener { _ -> openEventInBrowser() }
    }

    private fun openEventInBrowser() {
        val eventsDao = appDatabase.getEventsDao()
        val link = eventsDao.getEventById(event_info_id.text.toString().toInt()).Link
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}
