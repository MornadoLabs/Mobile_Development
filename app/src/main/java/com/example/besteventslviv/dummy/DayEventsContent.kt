package com.example.besteventslviv.dummy

import com.example.besteventslviv.Models.DayEvent
import java.util.ArrayList
import java.util.HashMap

object DayEventsContent {

    /**
     * An array of sample items.
     */
    val ITEMS: MutableList<DayEvent> = ArrayList()

    /**
     * A map of sample items, by GroupID.
     */
    val ITEM_MAP: MutableMap<Int, DayEvent> = HashMap()

    fun addItem(item: DayEvent) {
        ITEMS.add(item)
        ITEM_MAP.put(item.UserEventID, item)
    }

    fun addItems(items: List<DayEvent>) {
        ITEMS.addAll(items)
        ITEM_MAP.putAll(items.associateBy { item -> item.UserEventID })
    }

    fun removeItem(item: DayEvent) {
        ITEMS.remove(item)
        ITEM_MAP.remove(item.UserEventID)
    }
}