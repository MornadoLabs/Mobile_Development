package com.example.besteventslviv.Fragments.ListFactories

import android.support.v7.widget.RecyclerView
import com.example.besteventslviv.Database.AppDatabase
import com.example.besteventslviv.Database.Dao.ItemListDao
import com.example.besteventslviv.Fragments.Adapters.DayEventsListRecyclerViewAdapter
import com.example.besteventslviv.Fragments.CustomDataListFragment
import com.example.besteventslviv.Models.DayEvent
import com.example.besteventslviv.StaticCache
import com.example.besteventslviv.dummy.DayEventsContent
import java.lang.Exception

class DayEventsListFactory: ListFactory() {
    override fun getAdapter(
        listItems: List<*>,
        listener: CustomDataListFragment.OnListFragmentInteractionListener<*>
    ): RecyclerView.Adapter<*> {
        try {
            listItems as List<DayEvent>
            listener as CustomDataListFragment.OnListFragmentInteractionListener<DayEvent>

            DayEventsContent.addItems(listItems)

            return DayEventsListRecyclerViewAdapter(
                DayEventsContent.ITEMS,
                listener
            )
        }
        catch (e: Exception){
            throw IllegalArgumentException()
        }
    }

    override fun getDao(appDatabase: AppDatabase): ItemListDao<*> {
        return appDatabase.getUserEventsDao()
    }

    override fun getArgs(): Any? {
        return StaticCache.UserID
    }
}