package com.example.besteventslviv.Fragments.ListFactories

import android.support.v7.widget.RecyclerView
import com.example.besteventslviv.Database.AppDatabase
import com.example.besteventslviv.Database.Dao.ItemListDao
import com.example.besteventslviv.Database.Entities.Event
import com.example.besteventslviv.Fragments.Adapters.EventsListRecyclerViewAdapter
import com.example.besteventslviv.Fragments.CustomDataListFragment
import com.example.besteventslviv.StaticCache
import com.example.besteventslviv.dummy.EventsContent
import java.lang.Exception

class EventsListFactory: ListFactory() {
    override fun getAdapter(
        listItems: List<*>,
        listener: CustomDataListFragment.OnListFragmentInteractionListener<*>
    ): RecyclerView.Adapter<*> {
        try {
            listItems as List<Event>
            listener as CustomDataListFragment.OnListFragmentInteractionListener<Event>

            EventsContent.clear()
            EventsContent.addItems(listItems)

            return EventsListRecyclerViewAdapter(
                EventsContent.ITEMS,
                listener
            )
        }
        catch (e: Exception){
            throw IllegalArgumentException()
        }
    }

    override fun getDao(appDatabase: AppDatabase): ItemListDao<*> {
        return appDatabase.getEventsDao()
    }

    override fun getArgs(): Any? {
        return StaticCache.GroupID
    }
}