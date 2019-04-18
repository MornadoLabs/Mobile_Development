package com.example.besteventslviv.dummy

import com.example.besteventslviv.Database.Entities.Event
import java.util.ArrayList
import java.util.HashMap

object EventsContent {

    /**
     * An array of sample items.
     */
    val ITEMS: MutableList<Event> = ArrayList()

    /**
     * A map of sample items, by GroupID.
     */
    val ITEM_MAP: MutableMap<Int, Event> = HashMap()

    fun addItem(item: Event) {
        ITEMS.add(item)
        ITEM_MAP.put(item.ID, item)
    }

    fun addItems(items: List<Event>) {
        ITEMS.addAll(items)
        ITEM_MAP.putAll(items.associateBy { item -> item.ID })
    }

    fun removeItem(item: Event) {
        ITEMS.remove(item)
        ITEM_MAP.remove(item.ID)
    }

    fun clear() {
        ITEMS.clear()
        ITEM_MAP.clear()
    }
}