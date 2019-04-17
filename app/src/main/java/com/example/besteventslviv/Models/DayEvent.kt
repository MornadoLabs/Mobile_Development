package com.example.besteventslviv.Models

import android.arch.persistence.room.Embedded
import com.example.besteventslviv.Database.Entities.Event

data class DayEvent (
    var UserEventID: Int,
    @Embedded
    var Event: Event,
    var Notify: Boolean
)