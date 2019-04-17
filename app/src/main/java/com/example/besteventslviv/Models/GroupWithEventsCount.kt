package com.example.besteventslviv.Models

import android.arch.persistence.room.Embedded
import com.example.besteventslviv.Database.Entities.Group

data class GroupWithEventsCount (
    @Embedded
    var Group: Group,
    var EventsCount: Int
)