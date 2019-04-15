package com.example.besteventslviv.Database.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "Events",
    foreignKeys = arrayOf(
        ForeignKey(entity = Group::class, parentColumns = arrayOf("ID"), childColumns = arrayOf("GroupID"), onDelete = CASCADE)
    )
)
data class Event (
    @PrimaryKey(autoGenerate = true)
    var ID: Int,
    var Name: String,
    var GroupID: Int,
    var Location: String,
    var Date: Date,
    var Description: String,
    var TicketsPrice: String,
    var Link: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var Image: ByteArray
)