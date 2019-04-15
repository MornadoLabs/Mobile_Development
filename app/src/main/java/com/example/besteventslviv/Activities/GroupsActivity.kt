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

    var listFragment: CustomDataListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)
    }

    override fun onStart() {
        super.onStart()
        listFragment = CustomDataListFragment.newInstance(ListType.Groups)
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
