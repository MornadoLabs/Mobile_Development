package com.example.besteventslviv.Database.Dao

import android.arch.persistence.room.*
import com.example.besteventslviv.Database.Entities.UserEvent
import com.example.besteventslviv.Models.DayEvent
import java.util.*

@Dao
interface UserEventsDao: ItemListDao<DayEvent> {
    @Insert
    fun Insert(userEvent: UserEvent): Long

    @Insert
    fun Insert(userEvents: List<UserEvent>): List<Long>

    @Update
    fun Update(userEvent: UserEvent)

    @Update
    fun Update(userEvents: List<UserEvent>): Int

    @Delete
    fun Delete(userEvent: UserEvent)

    @Delete
    fun Delete(userEvents: List<UserEvent>): Int

    @Query("SELECT * FROM UserEvents WHERE ID = :Id")
    fun getUserEventByID(Id: Int): UserEvent?

    @Query("SELECT * FROM UserEvents WHERE UserID = :userID AND EventID = :eventID")
    fun getUserEventByUserIDAndEventID(userID: Int, eventID: Int): UserEvent?

    @Query("SELECT ue.ID as UserEventID, e.*, ue.Notify FROM UserEvents ue JOIN Events e ON ue.EventID = e.ID JOIN Users u ON ue.UserID = u.ID WHERE u.ID = :arg1")
    override fun getListItems(arg1: Int?): List<DayEvent>
}