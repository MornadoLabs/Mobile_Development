package com.example.besteventslviv.Database.Entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Users")
data class User (
    @PrimaryKey(autoGenerate = true)
    var ID: Int,
    var Login: String,
    var Password: String,
    var Email: String
)