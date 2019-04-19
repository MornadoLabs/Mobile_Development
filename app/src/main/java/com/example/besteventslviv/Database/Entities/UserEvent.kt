package com.example.besteventslviv.Database.Entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = "UserEvents",
    foreignKeys = arrayOf(
        ForeignKey(entity = User::class, parentColumns = arrayOf("ID"), childColumns = arrayOf("UserID")),
        ForeignKey(entity = Event::class, parentColumns = arrayOf("ID"), childColumns = arrayOf("EventID"))
    ),
    indices = arrayOf(
        Index(value = arrayOf("UserID", "EventID"), name = "IX_UserEvents_UserID_EventID", unique = true)
    )
)
data class UserEvent (
    var UserID: Int,
    var EventID: Int,
    var Notify: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0
}