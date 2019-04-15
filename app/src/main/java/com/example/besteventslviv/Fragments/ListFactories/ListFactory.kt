package com.example.besteventslviv.Fragments.ListFactories

import android.support.v7.widget.RecyclerView
import com.example.besteventslviv.Database.AppDatabase
import com.example.besteventslviv.Database.Dao.ItemListDao
import com.example.besteventslviv.Fragments.CustomDataListFragment

abstract class ListFactory {
    abstract fun getAdapter(listItems: List<*>, listener: CustomDataListFragment.OnListFragmentInteractionListener<*>): RecyclerView.Adapter<*>
    abstract fun getDao(appDatabase: AppDatabase): ItemListDao<*>
    abstract fun getArgs(): Any?

    companion object {
        fun getFactory(listType: ListType): ListFactory? {
            when (listType) {
                ListType.Groups -> return GroupsListFactory()
                ListType.Events -> return EventsListFactory()
                ListType.DayEvents -> return DayEventsListFactory()
            }

            return null
        }
    }
}