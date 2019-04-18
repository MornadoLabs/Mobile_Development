package com.example.besteventslviv.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.besteventslviv.Fragments.CustomDataListFragment
import com.example.besteventslviv.Fragments.ListFactories.ListType
import com.example.besteventslviv.Models.GroupWithEventsCount
import com.example.besteventslviv.R
import com.example.besteventslviv.StaticCache



class GroupsActivity :
    AppCompatActivity(),
    CustomDataListFragment.OnListFragmentInteractionListener<GroupWithEventsCount> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.groups_fragment_container, CustomDataListFragment.newInstance(ListType.Groups))
                .commit()
        }
    }

    override fun onListFragmentSelect(item: GroupWithEventsCount?) {
        if (item == null) {
            throw NullPointerException("Group can't be null")
        }

        StaticCache.GroupID = item.Group.ID

        val intent = Intent(this, EventsActivity::class.java)
        startActivity(intent)
    }

    override fun onListFragmentInteraction(item: GroupWithEventsCount?){}

    override fun onListFragmentDelete(item: GroupWithEventsCount?) {}

}
