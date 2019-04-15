package com.example.besteventslviv.Database.Dao

interface ItemListDao<T> {
    fun getListItems(arg1: Any? = null): List<T>
}