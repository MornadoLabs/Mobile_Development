package com.example.besteventslviv.Database.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = "Groups",
    indices = arrayOf(
        Index(value = arrayOf("Name"), name = "IX_Groups_Name", unique = true)
    ))
data class Group (
    var Name: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var Image: ByteArray
) {
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0
}