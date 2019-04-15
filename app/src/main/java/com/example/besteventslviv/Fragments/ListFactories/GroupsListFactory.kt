package com.example.besteventslviv.Fragments.ListFactories

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.besteventslviv.Database.AppDatabase
import com.example.besteventslviv.Database.Dao.ItemListDao
import com.example.besteventslviv.Fragments.Adapters.GroupsListRecyclerViewAdapter
import com.example.besteventslviv.Fragments.CustomDataListFragment
import com.example.besteventslviv.Models.GroupWithEventsCount
import com.example.besteventslviv.R
import com.example.besteventslviv.dummy.GroupsContent
import java.lang.Exception

class GroupsListFactory: ListFactory() {
    override fun getAdapter(
        listItems: List<*>,
        listener: CustomDataListFragment.OnListFragmentInteractionListener<*>
    ): RecyclerView.Adapter<*> {
        try {
            listItems as List<GroupWithEventsCount>
            listener as CustomDataListFragment.OnListFragmentInteractionListener<GroupWithEventsCount>

            GroupsContent.addItems(listItems)

            return GroupsListRecyclerViewAdapter(
                GroupsContent.ITEMS,
                listener
            )
        }
        catch (e: Exception){
            throw IllegalArgumentException()
        }
    }

    override fun getDao(appDatabase: AppDatabase): ItemListDao<*> {
        return appDatabase.GetGroupsDao()
    }

    override fun getArgs(): Any? {
        return null
    }
}