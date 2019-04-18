package com.example.besteventslviv.Database.Dao

import android.arch.persistence.room.*
import com.example.besteventslviv.Database.Entities.User

@Dao
interface UsersDao {
    @Insert
    fun Insert(user: User): Long

    @Insert
    fun Insert(users: List<User>): List<Long>

    @Update
    fun Update(user: User)

    @Update
    fun Update(users: List<User>): Int

    @Delete
    fun Delete(user: User)

    @Delete
    fun Delete(users: List<User>): Int

    @Query("SELECT * FROM Users WHERE Login = :login")
    fun getUserByLogin(login: String): User?

    @Query("SELECT * FROM Users")
    fun getAllUsers(): List<User>
}