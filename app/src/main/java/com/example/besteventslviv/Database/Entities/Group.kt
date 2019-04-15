package com.example.besteventslviv.Database.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Groups")
data class Group (
    @PrimaryKey(autoGenerate = true)
    var ID: Int,
    var Name: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var Image: ByteArray
)