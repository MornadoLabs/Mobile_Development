package com.example.besteventslviv.Database.Entities

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import java.util.*

@Entity(
    tableName = "Events",
    foreignKeys = arrayOf(
        ForeignKey(entity = Group::class, parentColumns = arrayOf("ID"), childColumns = arrayOf("GroupID"), onDelete = CASCADE)
    ),
    indices = arrayOf(
        Index(value = arrayOf("GroupID"), name = "IX_Events_GroupID"),
        Index(value = arrayOf("Name"), name = "IX_Events_Name", unique = true)
    )
)
data class Event (
    var Name: String,
    var GroupID: Int,
    var Location: String,
    var Date: Date,
    var Description: String,
    var TicketsPrice: String,
    var Link: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var Image: ByteArray
) {
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0
}