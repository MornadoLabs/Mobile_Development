package com.example.besteventslviv.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.besteventslviv.R
import com.example.besteventslviv.StaticCache

open class BaseActivity: AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            R.id.groups_menu_item ->{
                openGroups()
                return true
            }
            R.id.day_events_menu_item -> {
                openDayEvents()
                return true
            }
            R.id.log_out_menu_item -> {
                logOut()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    protected fun openGroups(){
        val intent = Intent(this, GroupsActivity::class.java)
        startActivity(intent)
    }

    protected fun openDayEvents() {
        val intent = Intent(this, DayEventsActivity::class.java)
        startActivity(intent)
    }

    protected fun logOut() {
        StaticCache.clear()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}