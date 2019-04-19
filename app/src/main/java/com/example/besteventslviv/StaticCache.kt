package com.example.besteventslviv

object StaticCache {
    var UserID: Int? = null
    var GroupID: Int? = null
    var EventID: Int? = null

    fun clear() {
        UserID = null
        GroupID = null
        EventID = null
    }
}