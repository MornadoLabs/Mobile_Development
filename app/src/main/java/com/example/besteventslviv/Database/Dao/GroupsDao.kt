package com.example.besteventslviv.Database.Dao

import android.arch.persistence.room.*
import com.example.besteventslviv.Database.Entities.Group
import com.example.besteventslviv.Models.GroupWithEventsCount

@Dao
interface GroupsDao: ItemListDao<GroupWithEventsCount> {
    @Insert
    fun Insert(group: Group): Int

    @Insert
    fun Insert(groups: List<Group>): Int

    @Update
    fun Update(group: Group)

    @Update
    fun Update(groups: List<Group>): Int

    @Delete
    fun Delete(group: Group)

    @Delete
    fun Delete(groups: List<Group>): Int

    @Query("SELECT * FROM Groups")
    fun getAll(): List<Group>

    @Query("SELECT g.*, COUNT(e.ID) FROM Groups g JOIN Events e ON g.ID = e.GroupID GROUP BY g.ID")
    override fun getListItems(arg1: Any?): List<GroupWithEventsCount>
}