package com.example.besteventslviv.Activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.example.besteventslviv.Database.AppDatabase
import com.example.besteventslviv.Database.Entities.UserEvent
import com.example.besteventslviv.Fragments.CustomDataListFragment
import com.example.besteventslviv.Fragments.ListFactories.ListType
import com.example.besteventslviv.Models.DayEvent
import com.example.besteventslviv.R
import com.example.besteventslviv.StaticCache
import com.example.besteventslviv.dummy.DayEventsContent

class DayEventsActivity : BaseActivity(), CustomDataListFragment.OnListFragmentInteractionListener<DayEvent> {

    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_events)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.day_events_fragment_container, CustomDataListFragment.newInstance(ListType.DayEvents))
                .commit()
        }

        appDatabase = AppDatabase.getAppDatabase(this.baseContext)!!
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            R.id.groups_menu_item -> {
                openGroups()
                return true
            }
            R.id.log_out_menu_item -> {
                logOut()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onListFragmentSelect(item: DayEvent?) {
        if (item == null) {
            throw NullPointerException("DayEvent can't be null")
        }

        StaticCache.EventID = item.Event.ID

        val intent = Intent(this, EventInfoActivity::class.java)
        startActivity(intent)
    }

    override fun onListFragmentInteraction(item: DayEvent?) {
        if (item == null) {
            throw NullPointerException("DayEvent can't be null")
        }

        val userEventsDao = appDatabase.getUserEventsDao()
        var currentEvent: UserEvent? = userEventsDao.getUserEventByID(item.UserEventID) ?: return
        currentEvent!!.Notify = item.Notify

        userEventsDao.Update(currentEvent)
    }

    override fun onListFragmentDelete(item: DayEvent?) {
        if (item == null) {
            throw NullPointerException("DayEvent can't be null")
        }

        val userEventsDao = appDatabase.getUserEventsDao()
        var currentEvent: UserEvent? = userEventsDao.getUserEventByID(item.UserEventID) ?: return

        DayEventsContent.removeItem(item)
        userEventsDao.Delete(currentEvent!!)

        openDayEvents()
    }
}
