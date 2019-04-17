package com.example.besteventslviv.Database.Dao

import android.arch.persistence.room.Dao

@Dao
interface ItemListDao<T> {
    fun getListItems(arg1: Int): List<T>
}