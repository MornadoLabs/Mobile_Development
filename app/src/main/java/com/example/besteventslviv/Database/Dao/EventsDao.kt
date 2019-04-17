package com.example.besteventslviv.Database.Dao

import android.arch.persistence.room.*
import com.example.besteventslviv.Database.Entities.Event

@Dao
interface EventsDao: ItemListDao<Event> {
    @Insert
    fun Insert(event: Event): Long

    @Insert
    fun Insert(events: List<Event>): List<Long>

    @Update
    fun Update(event: Event)

    @Update
    fun Update(events: List<Event>): Int

    @Delete
    fun Delete(event: Event)

    @Delete
    fun Delete(events: List<Event>): Int

    @Query("SELECT * FROM Events WHERE GroupID = :groupId")
    fun getEventsByGroup(groupId: Int): List<Event>

    @Query("SELECT * FROM Events WHERE ID = :eventId")
    fun getEventById(eventId: Int): Event

    @Query("SELECT * FROM Events WHERE GroupID = :arg1")
    override fun getListItems(arg1: Int): List<Event>
}