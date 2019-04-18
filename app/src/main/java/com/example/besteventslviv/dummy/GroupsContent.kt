package com.example.besteventslviv.dummy

import com.example.besteventslviv.Database.Entities.Group
import com.example.besteventslviv.Models.GroupWithEventsCount
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object GroupsContent {

    /**
     * An array of sample items.
     */
    val ITEMS: MutableList<GroupWithEventsCount> = ArrayList()

    /**
     * A map of sample items, by GroupID.
     */
    val ITEM_MAP: MutableMap<Int, GroupWithEventsCount> = HashMap()

    fun addItem(item: GroupWithEventsCount) {
        ITEMS.add(item)
        ITEM_MAP.put(item.Group.ID, item)
    }

    fun addItems(items: List<GroupWithEventsCount>) {
        ITEMS.addAll(items)
        ITEM_MAP.putAll(items.associateBy { item -> item.Group.ID })
    }

    fun removeItem(item: GroupWithEventsCount) {
        ITEMS.remove(item)
        ITEM_MAP.remove(item.Group.ID)
    }

    fun clear() {
        ITEMS.clear()
        ITEM_MAP.clear()
    }
}
