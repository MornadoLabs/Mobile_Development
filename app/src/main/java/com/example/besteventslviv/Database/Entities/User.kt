package com.example.besteventslviv.Database.Entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Users")
data class User (
    var Login: String,
    var Password: String,
    var Email: String
) {
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0
}