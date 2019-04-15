package com.example.besteventslviv.Database

import android.arch.persistence.room.*
import android.content.Context
import com.example.besteventslviv.Database.Dao.EventsDao
import com.example.besteventslviv.Database.Dao.GroupsDao
import com.example.besteventslviv.Database.Dao.UserEventsDao
import com.example.besteventslviv.Database.Dao.UsersDao
import com.example.besteventslviv.Database.Entities.Event
import com.example.besteventslviv.Database.Entities.Group
import com.example.besteventslviv.Database.Entities.User
import com.example.besteventslviv.Database.Entities.UserEvent

@Database(entities = arrayOf(
    User::class,
    Group::class,
    Event::class,
    UserEvent::class
), version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun GetUsersDao(): UsersDao
    abstract fun GetGroupsDao(): GroupsDao
    abstract fun GetEventsDao(): EventsDao
    abstract fun GetUserEventsDao(): UserEventsDao

    companion object {
        private var INSTANSE: AppDatabase? = null

        fun GetAppDatabase(context: Context): AppDatabase? {
            if (INSTANSE == null){
                synchronized(AppDatabase::class){
                    INSTANSE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "myDB").build()
                }
            }

            return INSTANSE
        }
    }
}