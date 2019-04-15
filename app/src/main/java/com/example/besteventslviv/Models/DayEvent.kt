package com.example.besteventslviv.Models

import com.example.besteventslviv.Database.Entities.Event

data class DayEvent (
    var UserEventID: Int,
    var Event: Event,
    var Notyfi: Boolean
)