package com.example.besteventslviv.Fragments.Adapters

import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import com.example.besteventslviv.Fragments.CustomDataListFragment.OnListFragmentInteractionListener
import com.example.besteventslviv.Models.GroupWithEventsCount
import com.example.besteventslviv.R

import kotlinx.android.synthetic.main.fragment_groups_list_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [GroupWithEventsCount] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class GroupsListRecyclerViewAdapter(
    private val mValues: List<GroupWithEventsCount>,
    private val mListener: OnListFragmentInteractionListener<GroupWithEventsCount>?
) : RecyclerView.Adapter<GroupsListRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as GroupWithEventsCount
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_groups_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.Group.ID.toString()
        holder.mNameView.text = item.Group.Name
        holder.mEventsCountView.text = item.EventsCount.toString()
        holder.mImageView.setImageBitmap(BitmapFactory.decodeByteArray(item.Group.Image, 0, item.Group.Image.size))

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.group_id
        val mNameView: TextView = mView.group_name
        val mEventsCountView: TextView = mView.events_count
        val mImageView: ImageView = mView.group_image

        override fun toString(): String {
            return super.toString() + " '${mNameView.text}' (${mEventsCountView.text})"
        }
    }
}
