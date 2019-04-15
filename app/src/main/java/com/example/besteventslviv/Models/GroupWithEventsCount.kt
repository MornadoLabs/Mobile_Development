package com.example.besteventslviv.Models

import com.example.besteventslviv.Database.Entities.Group

data class GroupWithEventsCount (
    var Group: Group,
    var EventsCount: Int
)