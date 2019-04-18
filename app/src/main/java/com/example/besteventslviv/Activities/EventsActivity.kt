package com.example.besteventslviv.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.besteventslviv.Database.Entities.Event
import com.example.besteventslviv.Fragments.CustomDataListFragment
import com.example.besteventslviv.Fragments.ListFactories.ListType
import com.example.besteventslviv.R
import com.example.besteventslviv.StaticCache

class EventsActivity :
    AppCompatActivity(),
    CustomDataListFragment.OnListFragmentInteractionListener<Event> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.events_fragment_container, CustomDataListFragment.newInstance(ListType.Events))
                .commit()
        }
    }

    override fun onListFragmentSelect(item: Event?) {
        if (item == null) {
            throw NullPointerException("Event can't be null")
        }

        StaticCache.EventID = item.ID

        val intent = Intent(this, EventInfoActivity::class.java)
        startActivity(intent)
    }

    override fun onListFragmentInteraction(item: Event?){}

    override fun onListFragmentDelete(item: Event?) {}

}
